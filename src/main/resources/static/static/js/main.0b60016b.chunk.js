(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{122:function(e,t,a){e.exports=a(310)},127:function(e,t,a){},128:function(e,t,a){},288:function(e,t,a){},308:function(e,t,a){},309:function(e,t,a){},310:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),o=a(28),i=a.n(o),s=(a(127),a(17)),c=a(18),l=a(20),m=a(19),u=a(21),h=a(313),d=a(115),p=a(314),g=a(3),v=a.n(g),E=a(23),f=a(29),b=a.n(f),y=a(10),j=a.n(y),O=a(24),C=a.n(O),w=a(32),k=a.n(w),x=a(25),S=a.n(x),I=(a(128),function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={name:"",password:""},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"handleButtonClick",value:function(){this.props.login(this.state.name)}},{key:"handleInputChange",value:function(e){switch(e.target.name){case"username":this.setState({name:e.target.value});break;case"password":this.setState({password:e.target.value})}}},{key:"render",value:function(){var e=this.props.classes;return r.a.createElement(v.a,{direction:"row",justify:"center",alignItems:"center",container:!0,className:"Login"},r.a.createElement(v.a,{item:!0,xs:11,md:4},r.a.createElement(b.a,{className:"login-wrapper",elevation:5},r.a.createElement(j.a,{variant:"h4",gutterBottom:!0},"USER LOGIN"),r.a.createElement(C.a,null),r.a.createElement(k.a,{className:e.loginItem,label:"Name",value:this.state.name,onChange:this.handleInputChange.bind(this),name:"username"}),r.a.createElement(k.a,{className:e.loginItem,label:"Password",value:this.state.password,onChange:this.handleInputChange.bind(this),name:"password",type:"password"}),r.a.createElement(S.a,{className:e.loginItem,size:"large",disabled:!1,onClick:this.handleButtonClick.bind(this),variant:"contained"},"Submit"))))}}]),t}(n.Component)),A=Object(E.withStyles)({loginItem:{marginBottom:"2vh"}})(I),T=a(70),B=Object(E.withStyles)({container:{padding:"1vh",marginTop:"2vh",textAlign:"center"}})(function(e){var t=e.classes,a=r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(T.a,{to:"/login",style:{textDecoration:"none"}},r.a.createElement(S.a,null,"Log in")),r.a.createElement("p",{style:{display:"inline-block",userSelect:"none"}},"|"),r.a.createElement(T.a,{to:"/register",style:{textDecoration:"none"}},r.a.createElement(S.a,null,"Register")));return e.loggedIn&&(a=r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(S.a,{onClick:e.logout},"Logout"),r.a.createElement("p",{style:{display:"inline-block",userSelect:"none"}},"|"),r.a.createElement(T.a,{to:"/new",style:{textDecoration:"none"}},r.a.createElement(S.a,null,"New Post")))),r.a.createElement(v.a,{direction:"row",justify:"space-between",alignItems:"center",container:!0,classes:{container:t.container}},r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(j.a,{variant:"h3",style:{textTransform:"uppercase"}},"Dickens Blog")),a)}),L=a(311),D=function(e){return r.a.createElement(v.a,{item:!0},r.a.createElement(L.a,{style:{textTransform:"uppercase"},to:e.url},e.children))},N=Object(E.withStyles)({container:{padding:"1vh",marginBottom:"1vh"}})(function(e){var t=e.classes;return r.a.createElement(v.a,{direction:"row",justify:"space-around",alignItems:"center",container:!0,classes:{container:t.container}},r.a.createElement(D,{url:"/"},"Home"),r.a.createElement(D,{url:"/category-1"},"Cat-1"),r.a.createElement(D,{url:"/category-2"},"Cat-2"),r.a.createElement(D,{url:"/category-3"},"Cat-3"),r.a.createElement(D,{url:"/category-4"},"Cat-4"),r.a.createElement(D,{url:"/category-5"},"Cat-5"),r.a.createElement(D,{url:"/category-6"},"Cat-6"),r.a.createElement(D,{url:"/category-7"},"Cat-7"))}),P=a(52),z=a.n(P);z.a.defaults.xsrfCookieName="csrftoken",z.a.defaults.xsrfHeaderName="X-CSRFToken";var W=z.a.create({baseURL:"http://localhost:8080/api"}),H=a(121),U=a(117),G={overrides:{h1:{component:function(e){return r.a.createElement(j.a,Object.assign({gutterBottom:!0,variant:"h4"},e))}},h2:{component:function(e){return r.a.createElement(j.a,Object.assign({gutterBottom:!0,variant:"h6"},e))}},h3:{component:function(e){return r.a.createElement(j.a,Object.assign({gutterBottom:!0,variant:"subtitle1"},e))}},h4:{component:function(e){return r.a.createElement(j.a,Object.assign({gutterBottom:!0,variant:"caption",paragraph:!0},e))}},p:{component:function(e){return r.a.createElement(j.a,Object.assign({paragraph:!0},e))}},li:{component:Object(E.withStyles)(function(e){return{listItem:{marginTop:e.spacing.unit}}})(function(e){var t=e.classes,a=Object(H.a)(e,["classes"]);return r.a.createElement("li",{className:t.listItem},r.a.createElement(j.a,Object.assign({component:"span"},a)))})}}};var M=function(e){return r.a.createElement(U.a,Object.assign({options:G},e))},R=(a(288),a(69)),F=a.n(R),J=Object(E.withStyles)({post:{padding:"1vh",margin:"1vh",minHeight:"30vh",width:"100%"},contentWrapper:{height:"21vh",overflow:"hidden",margin:"2vh"}})(function(e){var t=e.classes;return r.a.createElement(b.a,{classes:{root:t.post},elevation:5},r.a.createElement(v.a,{container:!0,direction:"row"},r.a.createElement(v.a,{container:!0,item:!0,xs:12,md:6,direction:"column",justify:"space-between"},r.a.createElement(v.a,{item:!0},r.a.createElement(j.a,{variant:"title"},e.data.title.toUpperCase()),r.a.createElement(C.a,null),r.a.createElement(j.a,{variant:"caption",classes:{root:t.contentWrapper}},r.a.createElement(M,null,e.data.body))),r.a.createElement(v.a,{container:!0,direction:"row",justify:"space-between"},r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(j.a,{variant:"body2"},"Author: ",e.data.author.userFirst," ",e.data.author.userLast),r.a.createElement(j.a,{variant:"body2"},new Date(e.data.postDate).toLocaleString("en-GB"))),r.a.createElement(v.a,{item:!0,container:!0,justify:"flex-end",xs:12,md:6},r.a.createElement(T.a,{to:"/post/"+e.data.postId,style:{textDecoration:"none"}},r.a.createElement(S.a,{size:"small",variant:"contained",color:"primary",onClick:function(){e.postOpen(e.data)}},"Read more!"))))),r.a.createElement(v.a,{container:!0,item:!0,xs:12,md:6,justify:"flex-end",alignItems:"center",style:{height:"35vh"}},r.a.createElement("img",{src:e.data.imgUrl,alt:"Title",className:"image"}))))}),V=function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={posts:a.props.posts},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this,t=this.state.posts.map(function(t){return console.log(t),r.a.createElement(J,{postOpen:e.props.postOpen,key:t.postId,data:t})});return r.a.createElement(v.a,{container:!0},t)}}]),t}(n.Component),q=a(50),X=function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={body:""},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"handleSubmit",value:function(){W.post("/comments/add/".concat(this.props.postId,"/1001"),this.state).then(function(e){return console.log(e)}).catch(function(e){return console.log(e)}).then(this.props.newComment())}},{key:"handleChange",value:function(e){this.setState({body:e.target.value})}},{key:"render",value:function(){return r.a.createElement(v.a,{item:!0,container:!0,xs:8,justify:"center",style:{marginTop:"1vh"}},r.a.createElement(j.a,{variant:"headline",style:{textDecoration:"underline"}},"Post a comment!"),r.a.createElement(k.a,{label:"Give your opinion on the text!",placeholder:"Comment",multiline:!0,margin:"normal",fullWidth:!0,onChange:this.handleChange.bind(this)}),r.a.createElement(S.a,{variant:"text",color:"primary",onClick:this.handleSubmit.bind(this)},"Submit!"))}}]),t}(n.Component),$=function(e){var t=e.comment;return console.log(t),r.a.createElement("div",null,r.a.createElement(j.a,{variant:"caption",style:{fontSize:"2vh",margin:"1vh 0",textAlign:"start",textTransform:"uppercase"}},t.author.userFirst),r.a.createElement(j.a,{variant:"body1"},t.body),r.a.createElement(j.a,{variant:"caption",style:{fontSize:"2vh",margin:"1vh 0",textAlign:"end"}},new Date(t.postDate).toLocaleString("en-GB")),r.a.createElement(C.a,null))},_=function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={comments:[]},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"componentDidMount",value:function(){this.setState({comments:this.props.comments})}},{key:"render",value:function(){var e=this.props.classes,t=this.state.comments.map(function(e){return r.a.createElement($,{key:e.commentId,comment:e})});return r.a.createElement(b.a,{elevation:5,classes:{root:e.comment}},r.a.createElement(j.a,{variant:"headline",style:{textDecoration:"underline"}},"Comments"),r.a.createElement(C.a,{style:{marginTop:"1vh"}}),t,r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(X,{postId:this.props.postId,newComment:this.props.newComment})))}}]),t}(n.Component),K=Object(E.withStyles)({comment:{padding:"1vh",margin:"1vh",boxSizing:"border-box"}})(_),Q=function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={post:{},comments:[],loadingPosts:!0,loadingComments:!0},a.converter=new q.Converter({tables:!0,simplifiedAutoLink:!0,strikethrough:!0,tasklists:!0}),a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"componentDidMount",value:function(){var e=this,t=this.props.match.params.id;W.get("/posts/"+t).then(function(t){e.setState({post:t.data,loadingPosts:!1})}),W.get("comments/"+t).then(function(t){e.setState({comments:t.data,loadingComments:!1})})}},{key:"loadComments",value:function(){var e=this,t=this.props.match.params.id;this.setState({loadingComments:!0},function(){W.get("comments/"+t).then(function(t){e.setState({comments:t.data,loadingComments:!1})})})}},{key:"render",value:function(){var e=this.props.classes;return this.state.loadingPosts||this.state.loadingComments?r.a.createElement(v.a,{item:!0,xs:11,lg:7},r.a.createElement("p",{style:{textAlign:"center"}},"Loading...")):r.a.createElement(v.a,{item:!0,xs:11,lg:7},r.a.createElement(v.a,{container:!0},r.a.createElement(b.a,{elevation:5,classes:{root:e.post}},r.a.createElement("img",{src:F.a,alt:"Header",style:{width:"100%"}}),r.a.createElement(j.a,{variant:"title",style:{fontSize:"3vh",margin:"1vh 0",textAlign:"center"}},this.state.post.title),r.a.createElement(C.a,null),r.a.createElement(M,null,this.state.post.body),r.a.createElement(C.a,null),r.a.createElement(j.a,{variant:"caption",style:{fontSize:"2vh",marginTop:"1vh",textAlign:"end"}},"Author: ",this.state.post.author.userFirst," ",this.state.post.author.userLast," - Written: ",new Date(this.state.post.postDate).toLocaleString("en-GB")))),r.a.createElement(v.a,{container:!0},r.a.createElement(K,{postId:this.state.post.postId,newComment:this.loadComments.bind(this),comments:this.state.comments})))}}]),t}(n.Component),Y=Object(E.withStyles)({post:{padding:"1vh",margin:"1vh",boxSizing:"border-box"},body:{margin:"1vh 0"}})(Q),Z=a(119),ee=a.n(Z),te=(a(307),function(e){function t(e){var a;return Object(s.a)(this,t),(a=Object(l.a)(this,Object(m.a)(t).call(this,e))).handleValueChange=function(e){a.setState({value:e}),a.props.onChange(e)},a.handleTabChange=function(e){a.setState({tab:e})},a.state={value:"",tab:"write"},a.converter=new q.Converter({tables:!0,simplifiedAutoLink:!0,strikethrough:!0,tasklists:!0}),a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this;return r.a.createElement("div",{className:"container"},r.a.createElement(ee.a,{onChange:this.handleValueChange,onTabChange:this.handleTabChange,value:this.state.value,generateMarkdownPreview:function(t){return Promise.resolve(e.converter.makeHtml(t))},selectedTab:this.state.tab}))}}]),t}(n.Component)),ae=function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={body:"",title:"",imgUrl:""},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"handleTitleChange",value:function(e){this.setState({title:e.target.value})}},{key:"handleImageChange",value:function(e){this.setState({imgUrl:e.target.value})}},{key:"handleBodyChange",value:function(e){this.setState({body:e})}},{key:"handleSend",value:function(){console.log(this.state),W.post("/posts/add/1001",this.state).then(function(e){return console.log(e)}).catch(function(e){return console.log(e)}).then(this.props.history.push("/"))}},{key:"handleCancel",value:function(){this.props.history.push("/")}},{key:"render",value:function(){var e=this.props.classes;return r.a.createElement(v.a,{item:!0,xs:12,container:!0,direction:"row",justify:"center"},r.a.createElement(v.a,{item:!0,md:4,sm:7,xs:10,classes:{item:e.title}},r.a.createElement(k.a,{label:"Post Title",fullWidth:!0,onChange:this.handleTitleChange.bind(this)})),r.a.createElement(v.a,{item:!0,md:4,sm:7,xs:10,classes:{item:e.title}},r.a.createElement(k.a,{label:"Title image url",fullWidth:!0,onChange:this.handleImageChange.bind(this)})),r.a.createElement(v.a,{item:!0,xs:12},r.a.createElement(C.a,null)),r.a.createElement(v.a,{item:!0,xs:12,classes:{item:e.editor}},r.a.createElement(te,{onChange:this.handleBodyChange.bind(this)})),r.a.createElement(v.a,{item:!0,xs:12},r.a.createElement(C.a,null)),r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(S.a,{onClick:this.handleSend.bind(this),size:"large",variant:"contained",color:"primary",classes:{root:e.button}},"Send"),r.a.createElement(S.a,{onClick:this.handleCancel.bind(this),size:"large",variant:"contained",color:"secondary",classes:{root:e.button}},"Cancel")))}}]),t}(n.Component),ne=Object(p.a)(Object(E.withStyles)({editor:{marginTop:"3vh",marginBottom:"3vh"},title:{margin:"0 2vh 3vh 2vh"},button:{margin:"3vh 3vh"}})(ae)),re=(a(308),function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={loading:!0,posts:{},modalOpen:!1,openPost:{}},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"componentDidMount",value:function(){var e=this;console.log("componentDidMount"),W.get("/posts/all").then(function(t){e.setState({posts:t.data,loading:!1})})}},{key:"handlePostOpen",value:function(e){var t=this;console.log(e),this.setState({openPost:e},function(){t.setState({modalOpen:!0})})}},{key:"render",value:function(){var e=this;return this.state.loading?r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"Home"},r.a.createElement("p",{style:{textAlign:"center"}},"Loading...")):r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"Home"},r.a.createElement(v.a,{item:!0,xs:11,md:8},r.a.createElement(b.a,{square:!0},r.a.createElement(B,{logout:this.props.logout,loggedIn:this.props.loggedIn}),r.a.createElement(C.a,{variant:"middle"}),r.a.createElement(N,null))),r.a.createElement(h.a,null,r.a.createElement(d.a,{path:"/",exact:!0,render:function(){return r.a.createElement(v.a,{item:!0,xs:11,md:7},r.a.createElement(V,{postOpen:e.handlePostOpen.bind(e),posts:e.state.posts}))}}),r.a.createElement(d.a,{path:"/new",render:function(){return r.a.createElement(v.a,{item:!0,xs:11,md:7},r.a.createElement(ne,{user:e.props.user}))}}),r.a.createElement(d.a,{path:"/post/:id",component:Y})))}}]),t}(n.Component)),oe=(a(309),function(e){function t(){var e,a;Object(s.a)(this,t);for(var n=arguments.length,r=new Array(n),o=0;o<n;o++)r[o]=arguments[o];return(a=Object(l.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={loggedIn:!1,name:""},a.handleLogin=function(e){a.setState({loggedIn:!0,name:e}),a.props.history.push("/")},a.handleLogout=function(){a.setState({loggedIn:!1,name:""}),a.props.history.push("/")},a}return Object(u.a)(t,e),Object(c.a)(t,[{key:"render",value:function(){var e=this;return r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"App"},r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(h.a,null,r.a.createElement(d.a,{path:"/login",render:function(){return r.a.createElement(A,{login:e.handleLogin.bind(e)})}}),r.a.createElement(d.a,{path:"/",render:function(){return r.a.createElement(re,{user:e.state.name,loggedIn:e.state.loggedIn,logout:e.handleLogout.bind(e)})}}))))}}]),t}(n.Component)),ie=Object(p.a)(oe),se=a(312);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var ce=r.a.createElement(se.a,null,r.a.createElement(ie,null));i.a.render(ce,document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})},69:function(e,t,a){e.exports=a.p+"static/media/header_placeholder.9adfc3a6.jpg"}},[[122,1,2]]]);
//# sourceMappingURL=main.0b60016b.chunk.js.map