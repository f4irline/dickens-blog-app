package com.github.dickens.blogapp;

import com.github.dickens.blogapp.utils.StringToEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Used to configure the MVC configuration, mappings, CORS and such.
 *
 * <p>
 * Working with React router and Spring Boot was a bit of a pain in the ass.
 * We had to include pretty heavy resolvers for paths so that for example refreshing and
 * such would work properly on the app.
 * </p>
 *
 * <p>
 * Issues would occur if we had built the ReactJS app and included it in this Spring app's static pages.
 * </p>
 *
 * <p>
 * We basically use the functionalities used in here:
 * https://github.com/geowarin/boot-react - to redirect EVERY page to index.html, which is the
 * ReactJS's main starting point.
 * </p>
 *
 * @author Tommi Lepola
 * @author Ville-Veikko Nieminen
 * @version 1.0
 * @since 2019.0403
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * Configure CORS globally to the whole app.
     *
     * @param registry the global registry with all the cors configuration for the app.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://localhost:3000", "http://localhost:3000", "https://dickens-blog-app.herokuapp.com")
                .allowedMethods("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH")
                .allowedHeaders("authorization, content-type, content-length, xsrf-token, credentials")
                .allowCredentials(true)
                .exposedHeaders("xsrf-token")
                .maxAge(3600);
    }

    /**
     * Add a converter which allows us to convert role parameter from
     * HTTP requests to Enums.
     *
     * @param registry the registry of field formatting logic.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEnumConverter());
    }

    /**
     * Used to add handlers to serve our static resources. Basically our ReactJS app when it's
     * bundled with the Spring backend.
     *
     * @param registry the registry which holds the resource handling logic.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(false)
                .addResolver(new PushStateResourceResolver());
    }

    /**
     * Provides functionalities for resolving incoming requests to our static resources.
     *
     * @author Tommi Lepola
     * @version 1.0
     * @since 2019.0403
     */
    private class PushStateResourceResolver implements ResourceResolver {
        private Resource index = new ClassPathResource("/static/index.html");
        private List<String> handledExtensions = Arrays.asList("html", "js", "json", "csv", "css", "png", "svg", "eot", "ttf", "woff", "appcache", "jpg", "jpeg", "gif", "ico");
        private List<String> ignoredPaths = Arrays.asList("api");

        @Override
        public Resource resolveResource(HttpServletRequest request, String requestPath, List<? extends Resource> locations, ResourceResolverChain chain) {
            return resolve(requestPath, locations);
        }

        @Override
        public String resolveUrlPath(String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
            Resource resolvedResource = resolve(resourcePath, locations);
            if (resolvedResource == null) {
                return null;
            }
            try {
                return resolvedResource.getURL().toString();
            } catch (IOException e) {
                return resolvedResource.getFilename();
            }
        }

        private Resource resolve(String requestPath, List<? extends Resource> locations) {
            if (isIgnored(requestPath)) {
                return null;
            }
            if (isHandled(requestPath)) {
                return locations.stream()
                        .map(loc -> createRelative(loc, requestPath))
                        .filter(resource -> resource != null && resource.exists())
                        .findFirst()
                        .orElseGet(null);
            }
            return index;
        }

        private Resource createRelative(Resource resource, String relativePath) {
            try {
                return resource.createRelative(relativePath);
            } catch (IOException e) {
                return null;
            }
        }

        private boolean isIgnored(String path) {
            return ignoredPaths.contains(path);
        }

        private boolean isHandled(String path) {
            String extension = StringUtils.getFilenameExtension(path);
            return handledExtensions.stream().anyMatch(ext -> ext.equals(extension));
        }
    }
}
