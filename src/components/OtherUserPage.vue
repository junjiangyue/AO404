<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div class="main">
      <div id="usercard">
        <div><img src="../../src/assets/personalpage.png" width="100%" class="bg-purple-light"></div>
        <div id="user-card">
          <div id="head-name">
            <p>
              <img style="border-radius: 50%;" :src="useravatar" width="70px" align="middle">
              <span id="bigname">{{userName}}</span>
              <el-button v-if="isMyFollow === 0" round plain type="primary" id="follow" @click="addFollow(userID)">+关注</el-button>
              <el-button v-else round plain type="info" id="follow" @click="openDialog(userID)">已关注</el-button>
              <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%">
                <span>确认取消关注该用户？</span>
                <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取 消</el-button>
                  <el-button type="primary" @click="cancleFollow(userID)">确 定</el-button>
                </span>
              </el-dialog>
            </p>
          </div>
        </div>
      </div>
      <div class="txt"><p >全部帖子（{{article_num}}个）</p></div>
      <div v-if="article_num==0" class="tip">{{tip}}</div>
      <div v-for="(item, index) in tabledata" :key="item.id">
        <div id="article-card">
          <p id="user-head">
            <img style="border-radius: 50%;" :src="useravatar"  width="50px" align="middle">
            <span>{{userName}}</span>
          </p>
          <p id="title" @click="gotoArticleInfo(item.articleId)">{{item.articleHeading}}</p>
          <hr align=center color=#EFEEEE SIZE=1 width="95%">
          <p id="content">{{item.articleContent}}</p>
          <img v-bind:src="item.picture" width="100px" id="picture">
          <div>
            <div id="time">
              <div class="alignment">{{item.publishTime}}</div>
              <div class="alignment">|</div>
              <div class="alignment" v-for="tag in item.tagList" :key="tag.id">
                <el-button type="text" @click="openTag(tag.tagId)" class="opentag-btn"># {{tag.tagName}}； </el-button>
              </div>
              <!--<i class="alignment" id="icon-like-other"></i>-->
              <img @click="like(item.articleId,index)" id="icon-like-other" v-if="item.isLiked === 0" src="@/assets/unlike.png"/>
              <img @click="like(item.articleId,index)" id="icon-like-other" v-else src="@/assets/like.png"/>
              <span class="alignment" id="likenum-other">{{item.articleLikes}}</span>
              <i class="alignment" id="icon-command-other"></i>
              <span class="alignment" id="commandnum-other">{{item.articleComments}}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'OtherUserPage',
  components: {
    Guidebar
  },
  mounted:function(){
    console.log(this.$route.query.userID);
    this.userID=this.$route.query.userID;
    console.log('userID',this.userID);
    this.getUserInfo(),
    this.getUserArticle(),
    this.getMyRelation(),
    this.$axios({
            method:"post",
            url:'http://47.102.194.89:8080/picture/getAvatar',
            responseType: 'arraybuffer',
            headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
            console.log(res)
            console.log(res.data)
        this.useravatar = 'data:image/jpeg;base64,'+this.arrayBufferToBase64(res.data)
        // console.log(this.useravatar)
        })
  },
  methods: {
    getUserInfo(){
      this.$axios({
        method:"get",
        url: 'api/user/userInfo',
        params:{userId:this.userID},
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('该用户信息数据：', res.data);
        this.userName=res.data.data.userName;
      },err=>{
        console.log(err);
      })
    },
    getUserArticle(){
      console.log('我开始了');
      this.$axios({
        method:"get",
        url: 'api/article/userArticle',
        params:{userId:this.userID},
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('该用户文章数据：', res.data);
        this.article_num=res.data.data.userArtList.length;
        this.tabledata=res.data.data.userArtList;
      },err=>{
        console.log(err);
      });
      console.log('我结束了');
    },
    getMyRelation(){
      this.$axios({
        method:"post",
        url: 'api/user/getMyRelation',
        params: {},
        headers: { token:window.sessionStorage.getItem("token")},
      }).then(res=>{
        console.log('关系列表信息',res.data);
        // this.follow_num = res.data.data.followlist.length;
        this.followdata = res.data.data.followList;
        console.log('关注',res.data.data.followList);
        var i=0;
        for(i;i<res.data.data.followList.length;i++){
          if(this.userID==this.followdata[i].userId&&this.followdata[i].isMyFollow==1){
            this.isMyFollow=1;
            console.log('是我的关注');
          }
        }
      });
    },
    addFollow(id) {
      console.log('要关注的id',id),
      this.$axios({
        method:"post",
        url: 'api/user/follow',
        params: {followingId:id},
        headers: { token:window.sessionStorage.getItem("token")},
      }).then(res=>{
        console.log(res.data);
        if(res.data.msg=="Success"){
          this.$message({
            message: '关注成功',
            type: 'success'
          });
          this.isMyFollow=1;
        }
      })
    },
    cancleFollow(id) {
      console.log('要取关的id',id)
      this.$axios({
        method:"post",
        url: 'api/user/unfollow',
        params:{
            followingId:id,
        },
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('取消关注数据：', res.data);
        if(res.data.msg=="Success"){
          this.$message({
            message: '取关成功',
            type: 'success'
          });
          this.dialogVisible = false;
          this.isMyFollow=0;
        }
      },err=>{
        console.log(err);
      });
    },
    openDialog(index) {
      this.dialogVisible = true,
      console.log("传值"+index),
      this.tempIndex=index
    },
    arrayBufferToBase64(buffer) {
                  var binary = '';
    var bytes = new Uint8Array( buffer );
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return window.btoa( binary );
            //   var binary = ''
            //   var bytes = new Uint8Array(buffer)
            //   var len = bytes.byteLength
            //   for(var i = 0; i < len; i++) {
            //       binary += String.fromCharCode(bytes[i])
            //   }
            //   return window.btoa(binary)
          },
    openTag(id) {
      console.log('openTagId',id);
      this.$router.push({path:'/Tag',query:{tagID:id}});
    },
    gotoArticleInfo(articleId){
      console.log(articleId),
      this.$router.push({path:'/ArticleInfo',query:{articleId:articleId}});
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
  },
  data() {
    return {
      userID:'',
      userName: '',
      // pic: require('../../src/assets/mlogo.png'),
      useravatar: '',
      article_num:'',
      isMyFollow:0,
      dialogVisible: false,
      tip:'这里空空如也，去其他用户的主页看看吧~',
      tabledata: [{
          articleId: 1,
          articleHeading: '',
          articleContent: '',
          picture: require('../../src/assets/discover_pic1.png'),
          publishTime: '2021-12-5',
          tag: '',
          articleComments: '',
          articleLikes: ''
        }],
      followdata:[{
        isMyFollow:'',
        userId:'',
      }]
    }
  }
}
</script>

<style>
  .alignment {
    display: inline-block;
    vertical-align: top;
  }
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
    padding: 10px;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .main{
    width: 1000px;
    margin: 0px auto;
    margin-top: 30px;
    padding-bottom: 60px;
  }
  #page-content {
    width: 1200px;
    margin: 0px auto;
    margin-top: 30px;
  }
  #usercard {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
  }
  #user-card {
    display: flex;
  }
  #head-name {
    align-self:flex-start;
    margin-left: 20px;
  }
  #bigname {
    font-size: 30px;
    margin-left: 20px;
  }
  .txt {
    margin-left: 25px;
  }
  .tip {
    margin-left: 25px;
    margin-top: 30px;
    font-size: 18px;
    color: #1a1b1c;
  }
  #article-card {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
    margin-top: 10px;
    margin-bottom: 20px;
  }
  #user-head {
    margin-left: 30px;
  }
  #title {
    margin-left: 30px;
    margin-top: 20px;
    margin-bottom: 0px;
    font-size: 18px;
  }
  #content {
    margin-left: 30px;
    margin-top: 0px;
    margin-bottom: 10px;
  }
  #picture {
    margin-left: 30px;
  }
  #icon-like-other {
    /*content: url(../../src/assets/like.png);*/
    width: 13px;
    position: absolute;
    right: 400px;

  }
  #likenum-other{
    position: absolute;
    right: 385px;
  }
  #icon-command-other {
    content: url(../../src/assets/command.png);
    width: 13px;
    position: absolute;
    right: 340px;
  }
  #commandnum-other{
    position: absolute;
    right: 325px;
  }
  #time {
    margin-left: 30px;
    font-size: 12px;
    flood-color: darkgray;
    margin-bottom: 10px;
  }
  #follow {
    margin-top: 10px;
    margin-left: 50px;
  }
  .opentag-btn {
    color: #606266;
    font-size: 12px;
    padding: 0px;
  }
</style>