(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{240:function(e,t,a){e.exports=a(437)},245:function(e,t,a){},265:function(e,t,a){},384:function(e,t,a){},385:function(e,t,a){},387:function(e,t,a){},435:function(e,t,a){},436:function(e,t,a){},437:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),s=a(32),o=a.n(s),i=(a(245),a(13)),l=a(14),c=a(16),m=a(15),h=a(17),u=a(440),d=a(154),p=a(441),g=a(3),v=a.n(g),E=a(12),f=a(47),y=a.n(f),b=a(34),w=a.n(b),C=a(28),k=a.n(C),j=a(6),O=a.n(j),S=a(24),x=a.n(S),N=a(29),I=a.n(N),P=a(23),L=a.n(P),T=a(69),D=a.n(T),A=a(70),F=a.n(A),R=a(77),B=a.n(R);B.a.defaults.xsrfCookieName="csrftoken",B.a.defaults.xsrfHeaderName="X-CSRFToken";var H=B.a.create({baseURL:"http://localhost:8080/api"}),M=(a(265),function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={userName:"",password:"",showPassword:!1,error:!1},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"handleButtonClick",value:function(){var e=this;H.post("/login?username=".concat(this.state.userName,"&password=").concat(this.state.password)).then(function(t){console.log(t),e.props.login()}).catch(function(t){console.log(t),e.setState({error:!0})})}},{key:"handleClickShowPassword",value:function(){this.setState({showPassword:!this.state.showPassword})}},{key:"handleInputChange",value:function(e){switch(e.target.name){case"userName":this.setState({userName:e.target.value});break;case"password":this.setState({password:e.target.value})}}},{key:"validator",value:function(){var e=!1,t=!1,a=!1;return this.state.userName.length<4&&(e=!0,a=!0),this.state.password.length<4&&(t=!0,a=!0),{userName:e,password:t,invalidFound:a}}},{key:"render",value:function(){var e=this.props.classes,t=this.validator();return r.a.createElement(v.a,{direction:"row",justify:"center",alignItems:"center",container:!0,className:"Login"},r.a.createElement(v.a,{item:!0,xs:11,md:4},r.a.createElement(k.a,{className:"login-wrapper",elevation:5},r.a.createElement(O.a,{variant:"h4",gutterBottom:!0},"USER LOGIN"),r.a.createElement(x.a,null),r.a.createElement(I.a,{error:t.userName,helperText:"Min. 4 characters",required:!0,className:e.loginItem,label:"Name",value:this.state.userName,onChange:this.handleInputChange.bind(this),name:"userName"}),r.a.createElement(I.a,{error:t.password,helperText:"Min. 4 characters",required:!0,className:e.loginItem,label:"Password",value:this.state.password,onChange:this.handleInputChange.bind(this),name:"password",type:this.state.showPassword?"text":"password",InputProps:{endAdornment:r.a.createElement(y.a,{position:"end"},r.a.createElement(w.a,{onClick:this.handleClickShowPassword.bind(this)},this.state.showPassword?r.a.createElement(D.a,null):r.a.createElement(F.a,null)))}}),r.a.createElement(L.a,{className:e.loginItem,size:"large",disabled:t.invalidFound,onClick:this.handleButtonClick.bind(this),variant:"contained"},"Submit"),this.state.error?r.a.createElement(O.a,{variant:"caption",style:{color:"#FF000"}},"Error in login."):null)))}}]),t}(n.Component)),z=Object(E.withStyles)({loginItem:{marginBottom:"2vh"}})(M),U=(a(384),function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={userName:"",password:"",userFirst:"",userLast:"",showPassword:!1},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"handleButtonClick",value:function(){var e={userName:this.state.userName,password:this.state.password,userFirst:this.state.userFirst,userLast:this.state.userLast};this.props.register(e)}},{key:"handleInputChange",value:function(e){switch(e.target.name){case"userName":this.setState({userName:e.target.value});break;case"password":this.setState({password:e.target.value});break;case"userFirst":this.setState({userFirst:e.target.value});break;case"userLast":this.setState({userLast:e.target.value})}}},{key:"handleClickShowPassword",value:function(){this.setState({showPassword:!this.state.showPassword})}},{key:"validator",value:function(){var e=!1,t=!1,a=!1,n=!1,r=!1;return this.state.userName.length<4&&(e=!0,r=!0),this.state.password.length<4&&(t=!0,r=!0),this.state.userFirst.length<2&&(a=!0,r=!0),this.state.userLast.length<2&&(n=!0,r=!0),{userName:e,password:t,firstName:a,lastName:n,invalidFound:r}}},{key:"render",value:function(){var e=this.props.classes,t=this.validator();return r.a.createElement(v.a,{direction:"row",justify:"center",alignItems:"center",container:!0,className:"Register"},r.a.createElement(v.a,{item:!0,xs:11,md:4},r.a.createElement(k.a,{className:"register-wrapper",elevation:5},r.a.createElement(O.a,{variant:"h4",gutterBottom:!0},"USER REGISTER"),r.a.createElement(x.a,null),r.a.createElement(I.a,{required:!0,error:t.userName,helperText:"Min. 4 characters",className:e.loginItem,label:"Name",value:this.state.userName,onChange:this.handleInputChange.bind(this),name:"userName"}),r.a.createElement(I.a,{required:!0,type:this.state.showPassword?"text":"password",error:t.password,helperText:"Min. 4 characters",className:e.loginItem,label:"Password",value:this.state.password,onChange:this.handleInputChange.bind(this),name:"password",InputProps:{endAdornment:r.a.createElement(y.a,{position:"end"},r.a.createElement(w.a,{onClick:this.handleClickShowPassword.bind(this)},this.state.showPassword?r.a.createElement(D.a,null):r.a.createElement(F.a,null)))}}),r.a.createElement(I.a,{required:!0,error:t.firstName,helperText:"Min. 2 characters",className:e.loginItem,label:"First Name",value:this.state.userFirst,onChange:this.handleInputChange.bind(this),name:"userFirst"}),r.a.createElement(I.a,{required:!0,error:t.lastName,helperText:"Min. 2 characters",className:e.loginItem,label:"Last Name",value:this.state.userLast,onChange:this.handleInputChange.bind(this),name:"userLast"}),r.a.createElement(L.a,{className:e.loginItem,size:"large",disabled:t.invalidFound,onClick:this.handleButtonClick.bind(this),variant:"contained"},"Submit"))))}}]),t}(n.Component)),W=Object(E.withStyles)({loginItem:{marginBottom:"2vh"}})(U),q=a(107),G=Object(E.withStyles)({container:{padding:"1vh",marginTop:"2vh",textAlign:"center"}})(function(e){var t=e.classes,a=r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(q.a,{to:"/login",style:{textDecoration:"none"}},r.a.createElement(L.a,{style:{color:"#555"}},"Log in")),r.a.createElement("p",{style:{display:"inline-block",userSelect:"none"}},"|"),r.a.createElement(q.a,{to:"/register",style:{textDecoration:"none"}},r.a.createElement(L.a,{style:{color:"#555"}},"Register")));return e.loggedIn&&(a=r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(L.a,{onClick:e.logout,style:{color:"#555"}},"Logout"),r.a.createElement("p",{style:{display:"inline-block",userSelect:"none"}},"|"),r.a.createElement(q.a,{to:"/new",style:{textDecoration:"none",color:"#444"}},r.a.createElement(L.a,null,"New Post")))),r.a.createElement(v.a,{direction:"row",justify:"space-between",alignItems:"center",container:!0,classes:{container:t.container}},r.a.createElement(v.a,{item:!0,xs:12,md:6},r.a.createElement(O.a,{variant:"h3",style:{textTransform:"uppercase",color:"#444"}},"Dickens Blog")),a)}),J=a(438),V=(a(385),function(e){return r.a.createElement(v.a,{className:"NavItem",item:!0},r.a.createElement(J.a,{className:"link-wrapper",to:e.url,exact:!0},e.icon,r.a.createElement(O.a,{variant:"subtitle1",classes:{root:"item"}},e.children)))}),X=a(156),$=a.n(X),_=a(159),K=a.n(_),Q=a(161),Y=a.n(Q),Z=a(160),ee=a.n(Z),te=a(158),ae=a.n(te),ne=a(157),re=a.n(ne),se=Object(E.withStyles)({container:{padding:"1vh",marginBottom:"1vh"}})(function(e){var t=e.classes;return r.a.createElement(v.a,{direction:"row",justify:"space-around",alignItems:"center",container:!0,classes:{container:t.container}},r.a.createElement(V,{url:"/",icon:r.a.createElement($.a,null)},"Home"),r.a.createElement(V,{url:"/category/tech",icon:r.a.createElement(re.a,null)},"Tech"),r.a.createElement(V,{url:"/category/studies",icon:r.a.createElement(ae.a,null)},"Studies"),r.a.createElement(V,{url:"/category/movies",icon:r.a.createElement(K.a,null)},"Movies"),r.a.createElement(V,{url:"/category/music",icon:r.a.createElement(ee.a,null)},"Music"),r.a.createElement(V,{url:"/category/health",icon:r.a.createElement(Y.a,null)},"Health"))}),oe=a(168),ie=a(162),le={overrides:{h1:{component:function(e){return r.a.createElement(O.a,Object.assign({gutterBottom:!0,variant:"h4"},e))}},h2:{component:function(e){return r.a.createElement(O.a,Object.assign({gutterBottom:!0,variant:"h6"},e))}},h3:{component:function(e){return r.a.createElement(O.a,Object.assign({gutterBottom:!0,variant:"subtitle1"},e))}},h4:{component:function(e){return r.a.createElement(O.a,Object.assign({gutterBottom:!0,variant:"caption",paragraph:!0},e))}},p:{component:function(e){return r.a.createElement(O.a,Object.assign({paragraph:!0},e))}},li:{component:Object(E.withStyles)(function(e){return{listItem:{marginTop:e.spacing.unit}}})(function(e){var t=e.classes,a=Object(oe.a)(e,["classes"]);return r.a.createElement("li",{className:t.listItem},r.a.createElement(O.a,Object.assign({component:"span"},a)))})}}};var ce=function(e){return r.a.createElement(ie.a,Object.assign({options:le},e))},me=a(73),he=a.n(me),ue=(a(387),Object(E.withStyles)({post:{padding:"1vh",margin:"1vh",minHeight:"30vh",width:"100%"},contentWrapper:{height:"21vh",overflow:"hidden",margin:"2vh"}})(function(e){var t=e.classes;return r.a.createElement(k.a,{classes:{root:t.post},elevation:5},r.a.createElement(v.a,{container:!0,direction:"row"},r.a.createElement(v.a,{container:!0,item:!0,xs:12,md:6,direction:"column",justify:"space-between"},r.a.createElement(v.a,{item:!0},r.a.createElement(O.a,{variant:"title"},e.data.title.toUpperCase()),r.a.createElement(x.a,null),r.a.createElement(O.a,{variant:"caption",classes:{root:t.contentWrapper}},r.a.createElement(ce,null,e.data.body))),r.a.createElement(v.a,{container:!0,direction:"row",justify:"space-between"},r.a.createElement(v.a,{item:!0,xs:6,md:6},r.a.createElement(O.a,{variant:"body2"},"Author: ",e.data.author.userFirst," ",e.data.author.userLast),r.a.createElement(O.a,{variant:"body2"},new Date(e.data.postDate).toLocaleString("en-GB"))),r.a.createElement(v.a,{item:!0,container:!0,justify:"flex-end",xs:6,md:6},r.a.createElement(q.a,{to:"/post/"+e.data.postId,style:{textDecoration:"none"}},r.a.createElement(L.a,{size:"small",variant:"contained",color:"primary",onClick:function(){e.postOpen(e.data)}},"Read more!"))))),r.a.createElement(v.a,{container:!0,item:!0,xs:12,md:6,justify:"center",alignItems:"center",style:{height:"35vh"}},r.a.createElement("img",{onError:function(e){e.target.src=he.a},src:e.data.imgUrl,alt:"Title",className:"image"}))))})),de=a(164),pe=function(e){return r.a.createElement("div",{className:"sweet-loading"},r.a.createElement(de.RingLoader,{sizeUnit:"em",size:15,color:"#2233FF",loading:e.loading}))},ge=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={loading:!0,posts:{}},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){void 0!==this.props.match.params?this.loadCategoryPosts(this.props.match.params):this.loadPosts()}},{key:"componentWillReceiveProps",value:function(e){this.props.match.params!==e.match.params&&void 0!==e.match.params&&this.loadCategoryPosts(e.match.params)}},{key:"loadPosts",value:function(){var e=this;this.setState({loading:!0},function(){H.get("/posts/all").then(function(t){e.setState({posts:t.data}),e.delayLoadingResolve()})})}},{key:"loadCategoryPosts",value:function(e){var t=this,a=e.category;void 0===a?this.loadPosts():this.setState({loading:!0},function(){H.get("/posts/category/".concat(a)).then(function(e){t.setState({posts:e.data}),t.delayLoadingResolve()})})}},{key:"delayLoadingResolve",value:function(){var e=this;setTimeout(function(){e.setState({loading:!1})},1500)}},{key:"render",value:function(){var e=this;if(this.state.loading)return r.a.createElement(v.a,{item:!0,container:!0,justify:"center",xs:12},r.a.createElement(pe,{loading:this.state.loading}));var t=this.state.posts.map(function(t){return r.a.createElement(ue,{postOpen:e.props.postOpen,key:t.postId,data:t})});return r.a.createElement(v.a,{container:!0},t)}}]),t}(n.Component),ve=Object(p.a)(ge),Ee=a(75),fe=a.n(Ee),ye=a(74),be=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={body:""},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"handleSubmit",value:function(){var e=this;H.post("/comments/add/".concat(this.props.postId,"/").concat(this.props.user.userId),this.state).then(function(t){console.log(t),e.props.newComment()}).catch(function(e){return console.log(e)})}},{key:"handleChange",value:function(e){this.setState({body:e.target.value})}},{key:"render",value:function(){return r.a.createElement(v.a,{item:!0,container:!0,xs:8,justify:"center",style:{marginTop:"1vh"}},r.a.createElement(O.a,{variant:"headline",style:{textDecoration:"underline"}},"Post a comment!"),r.a.createElement(I.a,{label:"Give your opinion!",placeholder:"Comment",multiline:!0,margin:"normal",fullWidth:!0,onChange:this.handleChange.bind(this)}),r.a.createElement(L.a,{variant:"text",color:"primary",onClick:this.handleSubmit.bind(this)},"Submit!"))}}]),t}(n.Component),we=a(64),Ce=a.n(we),ke=a(68),je=a.n(ke),Oe=a(66),Se=a.n(Oe),xe=a(67),Ne=a.n(xe),Ie=a(65),Pe=a.n(Ie),Le=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={open:!0,title:a.props.title,description:a.props.description},a.handleCancel=function(){a.props.handleClose("cancel")},a.handleDelete=function(){a.props.handleClose("delete")},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"render",value:function(){return r.a.createElement("div",null,r.a.createElement(Ce.a,{open:this.state.open,onClose:this.handleClose},r.a.createElement(Pe.a,null,this.state.title),r.a.createElement(Se.a,null,r.a.createElement(Ne.a,null,this.state.description)),r.a.createElement(je.a,null,r.a.createElement(L.a,{onClick:this.handleCancel,color:"secondary",name:"cancel"},"Cancel"),r.a.createElement(L.a,{onClick:this.handleDelete,color:"primary",name:"delete"},"Delete"))))}}]),t}(r.a.Component),Te=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={comment:a.props.comment,showDialog:!1},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"onClickOpenHandler",value:function(){this.setState({showDialog:!0})}},{key:"onClickCloseHandler",value:function(e){"delete"===e&&this.props.handleDelete(this.state.comment.commentId),this.setState({showDialog:!1})}},{key:"render",value:function(){return r.a.createElement("div",null,this.state.showDialog?r.a.createElement(Le,{title:"Remove comment?",description:"The comment will be removed. Are you sure?",handleClose:this.onClickCloseHandler.bind(this)}):null,r.a.createElement(O.a,{variant:"caption",style:{fontSize:"2vh",margin:"1vh 0",textAlign:"start",textTransform:"uppercase"}},this.state.comment.author.userFirst),r.a.createElement(O.a,{variant:"body1"},this.state.comment.body),r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"Home"},r.a.createElement(v.a,{item:!0,xs:6},r.a.createElement(w.a,{onClick:this.onClickOpenHandler.bind(this)},r.a.createElement(fe.a,{color:"secondary"}))),r.a.createElement(v.a,{item:!0,xs:6},r.a.createElement(O.a,{variant:"caption",style:{fontSize:"2vh",margin:"1vh 0",textAlign:"end"}},new Date(this.state.comment.postDate).toLocaleString("en-GB")))),r.a.createElement(x.a,null))}}]),t}(n.Component),De=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={comments:[]},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){this.setState({comments:this.props.comments})}},{key:"render",value:function(){var e=this,t=this.props.classes,a=this.state.comments.map(function(t){return r.a.createElement(Te,{handleDelete:e.props.handleDelete,key:t.commentId,comment:t})});return r.a.createElement(k.a,{elevation:5,classes:{root:t.comment}},r.a.createElement(O.a,{variant:"headline",style:{textDecoration:"underline"}},"Comments"),r.a.createElement(x.a,{style:{marginTop:"1vh"}}),a,r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(be,{user:this.props.user,postId:this.props.postId,newComment:this.props.newComment})))}}]),t}(n.Component),Ae=Object(E.withStyles)({comment:{padding:"1vh",margin:"1vh",boxSizing:"border-box",width:"100%"}})(De),Fe=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={post:{},comments:[],loadingPosts:!0,loadingComments:!0,showDialog:!1},a.converter=new ye.Converter({tables:!0,simplifiedAutoLink:!0,strikethrough:!0,tasklists:!0}),a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"componentDidMount",value:function(){var e=this,t=this.props.match.params.id;H.get("/posts/"+t).then(function(t){e.setState({post:t.data}),e.delayLoadingPostResolve()}),H.get("comments/"+t).then(function(t){e.setState({comments:t.data}),e.delayLoadingCommentsResolve()})}},{key:"loadComments",value:function(){var e=this,t=this.props.match.params.id;console.log("Loading"),this.setState({loadingComments:!0},function(){H.get("comments/"+t).then(function(t){e.setState({comments:t.data}),e.delayLoadingCommentsResolve()})})}},{key:"delayLoadingCommentsResolve",value:function(){var e=this;setTimeout(function(){e.setState({loadingComments:!1})},1500)}},{key:"delayLoadingPostResolve",value:function(){var e=this;setTimeout(function(){e.setState({loadingPosts:!1})},1500)}},{key:"deleteComment",value:function(e){H.delete("/comments/"+e).then(this.loadComments.bind(this))}},{key:"deletePostsComments",value:function(e){var t=this;H.delete("/comments/all/"+e).then(function(a){console.log(a),t.deletePost(e)}).catch(function(e){return console.log(e)})}},{key:"deletePost",value:function(e){var t=this;H.delete("/posts/"+e).then(function(e){console.log(e),t.props.history.push("/")}).catch(function(e){return console.log(e)})}},{key:"onClickOpenHandler",value:function(){this.setState({showDialog:!0})}},{key:"onClickCloseHandler",value:function(e){if("delete"===e){var t=this.props.match.params.id;this.deletePostsComments(t)}this.setState({showDialog:!1})}},{key:"addDefaultSrc",value:function(e){e.target.src=he.a}},{key:"render",value:function(){var e=this.props.classes;return this.state.loadingPosts||this.state.loadingComments?r.a.createElement(v.a,{item:!0,container:!0,justify:"center",xs:12},r.a.createElement(pe,{loading:!0})):r.a.createElement(v.a,{item:!0,xs:11,lg:7},this.state.showDialog?r.a.createElement(Le,{title:"Remove whole post?",description:"The whole post will be removed. Are you sure?",handleClose:this.onClickCloseHandler.bind(this)}):null,r.a.createElement(v.a,{container:!0},r.a.createElement(k.a,{elevation:5,classes:{root:e.post}},r.a.createElement("img",{onError:this.addDefaultSrc,src:this.state.post.imgUrl,alt:"Header",style:{width:"100%"}}),r.a.createElement(O.a,{variant:"title",style:{fontSize:"3vh",margin:"1vh 0",textAlign:"center"}},this.state.post.title),r.a.createElement(x.a,null),r.a.createElement(ce,null,this.state.post.body),r.a.createElement(x.a,null),r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"Home"},r.a.createElement(v.a,{item:!0,xs:6},r.a.createElement(w.a,{onClick:this.onClickOpenHandler.bind(this)},r.a.createElement(fe.a,{color:"secondary"}))),r.a.createElement(v.a,{item:!0,xs:6},r.a.createElement(O.a,{variant:"caption",style:{fontSize:"2vh",marginTop:"1vh",textAlign:"end"}},"Author: ",this.state.post.author.userFirst," ",this.state.post.author.userLast," - Written: ",new Date(this.state.post.postDate).toLocaleString("en-GB")))))),r.a.createElement(v.a,{container:!0},r.a.createElement(Ae,{user:this.props.user,handleDelete:this.deleteComment.bind(this),postId:this.state.post.postId,newComment:this.loadComments.bind(this),comments:this.state.comments})))}}]),t}(n.Component),Re=Object(p.a)(Object(E.withStyles)({post:{padding:"1vh",margin:"1vh",boxSizing:"border-box"},body:{margin:"1vh 0"}})(Fe)),Be=a(42),He=a.n(Be),Me=a(52),ze=a.n(Me),Ue=a(53),We=a.n(Ue),qe=a(54),Ge=a.n(qe),Je=a(37),Ve=a.n(Je),Xe=a(166),$e=a.n(Xe),_e=(a(431),function(e){function t(e){var a;return Object(i.a)(this,t),(a=Object(c.a)(this,Object(m.a)(t).call(this,e))).handleValueChange=function(e){a.setState({value:e}),a.props.onChange(e)},a.handleTabChange=function(e){a.setState({tab:e})},a.state={value:"",tab:"write"},a.converter=new ye.Converter({tables:!0,simplifiedAutoLink:!0,strikethrough:!0,tasklists:!0}),a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"render",value:function(){var e=this;return r.a.createElement("div",{className:"container"},r.a.createElement($e.a,{onChange:this.handleValueChange,onTabChange:this.handleTabChange,value:this.state.value,generateMarkdownPreview:function(t){return Promise.resolve(e.converter.makeHtml(t))},selectedTab:this.state.tab}))}}]),t}(n.Component)),Ke=a(167),Qe=function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={body:"",title:"",category:"NONE",imgUrl:""},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"handleTitleChange",value:function(e){this.setState({title:e.target.value})}},{key:"handleImageChange",value:function(e){this.setState({imgUrl:e.target.value})}},{key:"handleCategoryChange",value:function(e){this.setState({category:e.target.value})}},{key:"handleBodyChange",value:function(e){this.setState({body:e})}},{key:"handleSend",value:function(){var e=this;console.log(this.state),H.post("/posts/add/".concat(this.props.user.userId),this.state).then(function(t){console.log(t),e.props.history.push("/")}).catch(function(e){return console.log(e)})}},{key:"handleCancel",value:function(){this.props.history.push("/")}},{key:"validator",value:function(){var e=!1,t=!1,a=!1;return this.state.title.length<4&&(t=!0,a=!0),this.state.body.length<500&&(e=!0,a=!0),{body:e,title:t,invalidFound:a}}},{key:"render",value:function(){var e=this.props.classes,t=this.validator(),a={color:"#000"};return t.body&&(a={color:"#FF0000"}),r.a.createElement(v.a,{item:!0,xs:12,container:!0,direction:"row",justify:"center"},r.a.createElement(v.a,{item:!0,md:3,sm:7,xs:10,classes:{item:e.title}},r.a.createElement(I.a,{error:t.title,helperText:"Min. 4 characters",required:!0,label:"Post Title",fullWidth:!0,onChange:this.handleTitleChange.bind(this)})),r.a.createElement(v.a,{item:!0,md:3,sm:7,xs:10,classes:{item:e.title}},r.a.createElement(I.a,{label:"Title image url (optional)",fullWidth:!0,onChange:this.handleImageChange.bind(this)})),r.a.createElement(v.a,{item:!0,md:3,sm:7,xs:10,classes:{item:e.title}},r.a.createElement(We.a,{fullWidth:!0},r.a.createElement(ze.a,{htmlFor:"category-label"},"Category (optional)"),r.a.createElement(Ge.a,{value:this.state.category,onChange:this.handleCategoryChange.bind(this),fullWidth:!0,input:r.a.createElement(He.a,{name:"category",id:"category-label"})},r.a.createElement(Ve.a,{value:"NONE"},r.a.createElement("em",null,"None")),r.a.createElement(Ve.a,{value:"CULTURE"},"Culture"),r.a.createElement(Ve.a,{value:"TECH"},"Tech"),r.a.createElement(Ve.a,{value:"POLITICS"},"Politics"),r.a.createElement(Ve.a,{value:"STUDIES"},"Studies"),r.a.createElement(Ve.a,{value:"HEALTH"},"Health")))),r.a.createElement(v.a,{item:!0,xs:12},r.a.createElement(x.a,null)),r.a.createElement(v.a,{item:!0,xs:12,classes:{item:e.editor}},r.a.createElement(_e,{onChange:this.handleBodyChange.bind(this)}),r.a.createElement(Ke.a,{style:a},"Minimum 500 characters")),r.a.createElement(v.a,{item:!0,xs:12},r.a.createElement(x.a,null)),r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(L.a,{onClick:this.handleSend.bind(this),disabled:t.invalidFound,size:"large",variant:"contained",color:"primary",classes:{root:e.button}},"Send"),r.a.createElement(L.a,{onClick:this.handleCancel.bind(this),size:"large",variant:"contained",color:"secondary",classes:{root:e.button}},"Cancel")))}}]),t}(n.Component),Ye=Object(p.a)(Object(E.withStyles)(function(e){return{editor:{marginTop:"3vh",marginBottom:"3vh"},title:{margin:"0 2vh 3vh 2vh",height:"100%"},button:{margin:"3vh 3vh"}}})(Qe)),Ze=(a(435),function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={modalOpen:!1,openPost:{}},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"handlePostOpen",value:function(e){var t=this;this.setState({openPost:e},function(){t.setState({modalOpen:!0})})}},{key:"render",value:function(){var e=this;return r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"Home"},r.a.createElement(v.a,{item:!0,xs:11,md:8},r.a.createElement(k.a,{square:!0},r.a.createElement(G,{logout:this.props.logout,loggedIn:this.props.loggedIn}),r.a.createElement(x.a,{variant:"middle"}),r.a.createElement(se,null))),r.a.createElement(u.a,null,r.a.createElement(d.a,{path:"/",exact:!0,render:function(){return r.a.createElement(v.a,{item:!0,xs:11,md:7},r.a.createElement(ve,{postOpen:e.handlePostOpen.bind(e)}))}}),r.a.createElement(d.a,{path:"/new",render:function(){return r.a.createElement(v.a,{item:!0,xs:11,md:7},r.a.createElement(Ye,{user:e.props.user}))}}),r.a.createElement(d.a,{path:"/post/:id",render:function(){return r.a.createElement(Re,{user:e.props.user})}}),r.a.createElement(d.a,{path:"/category/:category",render:function(){return r.a.createElement(v.a,{item:!0,xs:11,md:7},r.a.createElement(ve,{postOpen:e.handlePostOpen.bind(e)}))}})))}}]),t}(n.Component)),et=(a(436),function(e){function t(){var e,a;Object(i.a)(this,t);for(var n=arguments.length,r=new Array(n),s=0;s<n;s++)r[s]=arguments[s];return(a=Object(c.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={loggedIn:!1,user:{}},a.handleLogin=function(){H.get("/users/details").then(function(e){console.log(e),a.setState({loggedIn:!0,user:e.data.user}),a.props.history.push("/")}).catch(function(e){return console.log(e)})},a.handleLogout=function(){a.setState({loggedIn:!1,name:""}),a.props.history.push("/")},a.handleRegister=function(e){H.post("/users/add",e).then(function(e){console.log(e),a.props.history.push("/")}).catch(function(e){return console.log(e)})},a}return Object(h.a)(t,e),Object(l.a)(t,[{key:"render",value:function(){var e=this;return r.a.createElement(v.a,{direction:"row",justify:"center",container:!0,className:"App"},r.a.createElement(v.a,{container:!0,justify:"center"},r.a.createElement(u.a,null,r.a.createElement(d.a,{path:"/login",render:function(){return r.a.createElement(z,{login:e.handleLogin.bind(e)})}}),r.a.createElement(d.a,{path:"/register",render:function(){return r.a.createElement(W,{register:e.handleRegister.bind(e)})}}),r.a.createElement(d.a,{path:"/",render:function(){return r.a.createElement(Ze,{user:e.state.user,loggedIn:e.state.loggedIn,logout:e.handleLogout.bind(e)})}}))))}}]),t}(n.Component)),tt=Object(p.a)(et),at=a(439);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));var nt=r.a.createElement(at.a,null,r.a.createElement(tt,null));o.a.render(nt,document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})},73:function(e,t,a){e.exports=a.p+"static/media/header_placeholder.9adfc3a6.jpg"}},[[240,1,2]]]);
//# sourceMappingURL=main.dc8676e4.chunk.js.map