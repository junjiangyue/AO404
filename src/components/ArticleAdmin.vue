<template>
<div class="article_info">
    <div class="article">
        <el-card class="box-card">
             <div slot="header" class="clearfix">
                    <li><img style="border-radius: 50%;" class="writer_avatar" v-bind:src="'data:image/jpeg;base64,'+articleInformation.userAvatar"/></li>
                    <li><span class="writer_name">{{articleInformation.userName}}</span></li>
            </div>
            <div class="content">
                <div class="title">{{articleInformation.articleHeading}}</div>
                <div class="time">{{articleInformation.publishTime}}</div>
                <div class="article_content">
                    {{articleInformation.articleContent}}
                    <img class="picture" v-bind:src="articleInformation.picture" width="100%" height="100%"/>
                 </div>
                <div class="tag" v-for="(item) in articleInformation.tagList" :key="item.tagId">
                    <p  class="opentag-btn"># {{item.tagName}}</p></div>
            </div>
            <el-divider></el-divider>
            <div class="commentsbody">
            <div class="comments" v-for="(item) in commentContent" :key="item.commentId">
                <el-card class="comments-card">
                    <div class="cmter_info">
                <el-avatar v-bind:src="'data:image/jpeg;base64,'+item.userAvatar"></el-avatar>
                <span class="writer_name">{{item.userName}}</span>
                    </div>
                    <div class="comment_content">
                     {{item.commentInfo}}
                    </div>
                    <div class="comment_time">
                     {{item.commentTime}}  
                    </div>
                </el-card>
            </div>
            </div>
        </el-card>

    </div>
</div>
</template>

<script>

export default {
    name:'AricleAdmin',
  data(){
    return {
      articleId:'',
      userName:'',
      textarea:'',
      articleInformation:[],
      commentContent:[],
      myId:'',
    }
  },
  methods:{
  },
  mounted: function(){
    console.log(this.$route.query.articleId),
    this.$axios({
    method:"get",
    url: 'api/article/articleInfo',
    headers:{
    token:window.sessionStorage.getItem("token")},
    params:{
        articleId:this.$route. query.articleId,
    }
    }).then(res=>{
		console.log(res);
    if(res.data.code=='200')
    {
      this.articleInformation = res.data.data.articleInformation
    }
    else console.log(res.data.code);
		},err=>{
			console.log(err);
		}),

        this.$axios({
            method:"get",
            url:'api/article/articleComment',
            headers:{
    token:window.sessionStorage.getItem("token")},
    params:{
        articleId:this.$route. query.articleId,
        
    }
        }).then(res=>{
            console.log(res);
            this.commentContent = res.data.data.commentList;
        });
        this.$axios({
            method:"get",
            url: 'api/user/myInfo',
            headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
            console.log('我的信息数据:', res.data);
            this.myId=res.data.data.userId;
            console.log('userId',this.myId);
        },err=>{
            console.log(err);
        });
}
}
</script>

<style>
.commentsbody {
    width:100%;
    height: auto;
}
.writer_avatar{
    width:50px;
    height:50px;
}
.article{
    background-color: #EFEEEE;
    min-width: 1400px;
}
li{
    list-style-type:none;
    text-align: center;
}
.comments-card{
    width: auto;
    padding-left: 20px;
}
.title{
    font-size: 20px;
    font-weight:bold;
    margin-bottom: 10px;
}
.time{
    margin-bottom: 10px;
    font-size: 12px;
    color:#939498;
}
.comment_time{
    margin-top: 10px;
    font-size: 12px;
    color:#939498;
    margin-left: 40px;
}
.article_content{
     margin-bottom: 50px;
     font-family: "Hiragino Sans GB","Microsoft YaHei",\5FAE\8F6F\96C5\9ED1,tahoma,arial,simsun,\5B8B\4F53;
     font-size: 14px;
}
.content{
    padding-left:50px;
    padding-right:50px;
}
.box-card{
    margin-left:400px;
    margin-right:400px;

}
.writer_name{
    font-size: 14px;
    font-weight:bold;
}
.cmter_info{
    margin-bottom: 10px;
}
.comment_content{
    margin-left: 40px;
}
.opentag-btn {
    color: #606266;
    font-size: 15px;
  }
  .opentag-btn:hover {
    color: #5087f5;
  }
.commit{
    text-align: center;
    font-size: 12px;
    color: #686868;
  }
</style>