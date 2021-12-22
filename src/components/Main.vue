<template>
  <div class="hello">
    <Guidebar></Guidebar>
      <div class="page_content">
        <el-row>
          <el-col :span="16">
            <div class="article_list" v-for="(item, index) in tabledata" :key="item.articleId">
            <el-card class="box-card">
                <div  class="clearfix">
                  <el-row>
                    <el-col :span="3">
                      <img style="border-radius: 50%;" class="writer_avatar" @click="openOtherUserPage(item.userId)" :src="'data:image/jpeg;base64,'+item.userAvatar"/></el-col>
                      <el-col :span="6" style="padding-top: 10px">
                        <div class="writer_name"><el-button @click="openOtherUserPage(item.userId)" type="text" class="writer_name">{{item.userName}}</el-button></div>
                        <div style="font-size:12px;color:#939498">发布了动态</div></el-col>
                  </el-row>
                </div>
                <div class="content">
                    <div class="title" @click="gotoArticleInfo(item.articleId)">{{item.articleHeading}}</div>
                    <div class="time">{{item.publishTime}}</div>
                    <div class="article_content">
                      <el-row>
                        <el-col :span="16">
                      {{item.articleContent}}</el-col>
                        <img v-bind:src="item.picture" width="100%" height="100%"/>
                        </el-row>
                    </div>
                    <el-divider></el-divider>
                    <el-row>
                      <el-col :span="16">
                    <div class="tag" v-for="(tag) in item.tagList" :key="tag.tagId">
                      <el-button type="text" @click="openTag(tag.tagId)" class="opentag-btn"># {{tag.tagName}}</el-button>
                    </div></el-col>
                    <el-col :span="6">
                        <span style="margin-right:10px;">
                          <img @click="like(item.articleId,index)" class="icon-like" v-if="item.isLiked === 0" src="@/assets/unlike.png"/>
                           <img @click="like(item.articleId,index)" class="icon-like" v-else src="@/assets/like.png"/>{{item.articleLikes}}</span>
                        <span><img class="icon-command" @click="comment(item.articleId)" src="@/assets/command.png"/>{{item.articleComments}}</span>
                    </el-col>
                    </el-row>
                </div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="4">
            <div >
              <el-card class="upload" shadow="never" style="border:0px;width=100%; height=100%">
                <img  src="@/assets/arabica-1300.png" width="100%" height="100%" />
                <div style="text-align:center;font-size:14px;">在这里发布你的任何想法！</div>
                <el-row style="text-align:center;padding-left:35px;padding-top:20px">
                  <el-col span="10">
                      <img @click="jumppoatword" src="@/assets/posttext.png" width="50px" height="50px" />
                      <p style="margin:5px">文字</p>
                  </el-col>
                  <el-col span="10">
                      <img @click="jumppoatword"  src="@/assets/postimg.png" width="50px" height="50px" />
                      <p style="margin:5px">图片</p>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'Main',
  components: {
    Guidebar
    },
  data(){
    return {
      
      // useravatar:'',
      tabledata: [
        {
            useravatar:require('../../src/assets/mlogo.png'),
            userName: 'AO404官方小助手',
            articleHeading: '欢迎来到AO404!',
            publishTime:'2021-12-22 0:49',
            articleContent: '这里还什么都没有哦~去发现页看看吧~',
            tagList:'AO404',
            articleLikes:'0',
            articleComments:'0',
            picture:require('../../src/assets/discover_pic1.png'),
            articleId:'',
            isLiked:false,
            like_src:'',
          }
      ]
    }
  },
  methods:{
    jumppoatword() {
      this.$router.push('/Postword')
    },
    like(articleId,index){
      console.log(index);
      console.log(this.tabledata[index].isLiked);
      if (this.tabledata[index].isLiked == 0){ // 没有点赞过
          this.$axios({
          method:"post",
          url: 'api/article/likeArticle',
          headers:{
          token:window.sessionStorage.getItem("token")},
          params:{
          articleId:articleId,
          }
          }).then(res=>{
          console.log(res);
          //this.tabledata[index].like_src = require('../../src/assets/like.png');
          console.log("点赞成功");
          //console.log(this.tabledata[index].like_src);
          this.tabledata[index].isLiked = 1;
          this.tabledata[index].articleLikes +=1;
          },err=>{
            console.log(err);
          })
      }else{ // 取消点赞
          this.$axios({
          method:"post",
          url: 'api/article/unlikeArticle',
          headers:{
          token:window.sessionStorage.getItem("token")},
          params:{
          articleId:articleId,
          }
          }).then(res=>{
          this.tabledata[index].isLiked = 0;
          this.tabledata[index].articleLikes -=1;
          console.log(res);
          console.log("取消点赞成功");
          },err=>{
            console.log(err);
          })
      }
    },
    comment(articleId){
      this.$router.push({path:'/ArticleInfo',query:{articleId:articleId}});
    },
    gotoArticleInfo(articleId){
      console.log(articleId),
      this.$router.push({path:'/ArticleInfo',query:{articleId:articleId}});
    },
    openTag(id) {
      console.log('openTagId',id);
      this.$router.push({path:'/Tag',query:{tagID:id}});
    },
    openOtherUserPage(id){
      console.log('打开的userid',id);
      this.$router.push({path:'/OtherUserPage',query:{userID:id}});
    }
    // arrayBufferToBase64(buffer) {
    //               var binary = '';
    // var bytes = new Uint8Array( buffer );
    // var len = bytes.byteLength;
    // for (var i = 0; i < len; i++) {
    //     binary += String.fromCharCode( bytes[ i ] );
    // }
    // return window.btoa( binary );
    //         //   var binary = ''
    //         //   var bytes = new Uint8Array(buffer)
    //         //   var len = bytes.byteLength
    //         //   for(var i = 0; i < len; i++) {
    //         //       binary += String.fromCharCode(bytes[i])
    //         //   }
    //         //   return window.btoa(binary)
    //       }
  },
  mounted: function(){
    // this.$axios({
    //         method:"post",
    //         url:'http://47.102.194.89:8080/picture/getAvatar',
    //         responseType: 'arraybuffer',
    //         headers: { token:window.sessionStorage.getItem("token")}
    //     }).then(res=>{
    //         console.log(res)
    //         console.log(res.data)
    //     this.useravatar = 'data:image/jpeg;base64,'+this.arrayBufferToBase64(res.data)
    //     // console.log(this.useravatar)
    //     })

    this.$axios({
    method:"get",
    url: 'api/article/homeArticle',
    headers:{
    token:window.sessionStorage.getItem("token")}
    }).then(res=>{
		console.log(res);
    if(res.data.data.homeList.length == 0)
    {
      this.tabledata.articleHeading = '暂时没有关注任何人，去发现页看看吧~'
    }
    else{
       this.tabledata = res.data.data.homeList;
       var i = 0;
       console.log(res.data.data.homeList.length);
       var j=res.data.data.homeList.length;
       while(j!= 0 ){
         if(res.data.data.homeList[i].isLiked == 1){
            this.like_src = require('../../src/assets/like.png');
         }else{
           this.like_src = require('../../src/assets/unlike.png');
         }
         i = i+1;
         j = j-1;
         }
       }
		},err=>{
			console.log(err);
		})
}
}
</script>
<style scoped>
.upload{
  margin-left: 20px;
  margin-top: 20px;
  width: 350px;
  height: 600px;
}
.article_list{
  margin-top: 20px;
  width: 950px;
}

.writer_avatar{
    width:50px;
    height:50px;
    /* border-color: black; */
}
.clearfix{
    text-align: left;
    margin-left: 50px;
    margin-bottom: 20px;
}
.page_content{
  height: 100%;
  width: 100%;
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
    width: 600px;
    margin-left: 250px;
}
.writer_name{
    font-size: 14px;
    font-weight:bold;
    color: #000;
}
.writer_name:hover{
    color: rgb(53, 154, 248);
}
.picture{
  width: 200px;
  height: 200px;
}
.icon-like {
    width: 13px;
    padding-right:5px;
}
.icon-command {
    width: 13px;
    padding-right:5px;
  }
  .opentag-btn {
    color: #606266;
    font-size: 14px;
  }
  .opentag-btn:hover {
    color: #5087f5;
  }
</style>