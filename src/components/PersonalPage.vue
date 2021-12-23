<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div id="page-content">
      <el-row>
        <el-col :span="6">
          <div class="grid-content">
            <div id="menu">
              <el-row class="tac">
                <el-col class="tac-menu">
                  <div id="menutitle"><h id="menu-title">个人主页</h></div>
                  <hr color=#EFEEEE SIZE=1>
                  <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
                    <el-menu-item index="1" @click="personalpage">
                      <i class="icon-person"></i>
                      <span slot="title">我的主页</span>
                    </el-menu-item>
                    <el-menu-item index="2" @click="followlist">
                      <i class="icon-follow"></i>
                      <span slot="title">我的关注</span>
                    </el-menu-item>
                    <el-menu-item index="3" @click="fanlist">
                      <i class="icon-fan"></i>
                      <span slot="title">我的粉丝</span>
                    </el-menu-item>
                    <el-submenu index="4">
                      <template slot="title">
                        <i class="icon-setting"></i>
                        <span>账号管理</span>
                      </template>
                      <el-menu-item-group>
                        <el-menu-item index="4-1" @click="userinfo">账号资料</el-menu-item>
                        <el-menu-item index="4-2" @click="accountsecurity">账号安全</el-menu-item>
                      </el-menu-item-group>
                    </el-submenu>
                  </el-menu>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-col>
        <el-col :span="18">
          <div class="grid-content">
            <div id="usercard">
              <div><img src="../../src/assets/personalpage.png" width="100%" class="bg-purple-light"></div>
              <div id="user-card">
                <div id="head-name">
                  <p>
                    <img v-if="useravatar!='data:image/jpeg;base64,'" style="border-radius: 50%;" :src="'data:image/jpeg;base64,'+useravatar" width="70px" align="middle"/>
                    <img v-else style="border-radius: 50%;" src="@/assets/mlogo.png" width="70px" align="middle"/>
                    <span id="bigname">{{userName}}</span>
                  </p>
                </div>
              </div>
            </div>
            <div class="txt"><p >全部帖子（{{article_num}}个）</p></div>
            <div v-if="article_num==0" class="tip">{{tip}}</div>
            <div v-for="(item, index) in tabledata" :key="item.id">
              <div id="article-card">
                <p id="user-head">
                  <img v-if="item.userAvatar" style="border-radius: 50%;" :src="'data:image/jpeg;base64,'+item.userAvatar" width="50px" align="middle">
                  <img v-else style="border-radius: 50%;" src="@/assets/mlogo.png" width="50px" align="middle">
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
                    <img @click="like(item.articleId,index)" id="icon-like" v-if="item.isLiked === 0" src="@/assets/unlike.png"/>
                    <img @click="like(item.articleId,index)" id="icon-like" v-else src="@/assets/like.png"/>
                    <!--<i class="alignment" id="icon-like"></i>-->
                    <span class="alignment" id="likenum">{{item.articleLikes}}</span>
                    <i class="alignment" id="icon-command"></i>
                    <span class="alignment" id="commandnum">{{item.articleComments}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'PersonalPage',
  components: {
    Guidebar
  },
  mounted:function(){
    this.getMyInfo(),
    this.getMyArticle()
    // this.$axios({
    //     method:"post",
    //     url:'http://47.102.194.89:8080/picture/getAvatar',
    //     responseType: 'arraybuffer',
    //     headers: { token:window.sessionStorage.getItem("token")}
    // }).then(res=>{
    //     console.log(res)
    //     console.log(res.data)
    // this.useravatar = 'data:image/jpeg;base64,'+this.arrayBufferToBase64(res.data)
    // // console.log(this.useravatar)
    // })
  },
  methods: {
    gotoArticleInfo(articleId){
      console.log(articleId),
      this.$router.push({name:'ArticleInfo',query:{articleId:articleId}});
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    personalpage() {
      this.$router.push({path: '/PersonalPage'});
    },
    followlist() {
      this.$router.push('/FollowList');
    },
    fanlist() {
      this.$router.push({path: '/FanList'});
    },
    userinfo() {
      this.$router.push({path: '/UserInfo'});
    },
    accountsecurity() {
      this.$router.push({path: '/AccountSecurity'});
    },
    getMyArticle() {
      console.log('hahaha');
      this.$axios({
        method:"get",
        url: 'api/article/myArticle',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的文章数据：', res.data);
        this.tabledata=res.data.data.userArtList;
        this.article_num=res.data.data.userArtList.length;
        console.log('文章数量：',this.article_num);
      },err=>{
        console.log(err);
      });
      console.log('hahaha');
    },
    getMyInfo() {
      this.$axios({
        method:"get",
        url: 'api/user/myInfo',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的信息数据：', res.data);
        this.userName=res.data.data.userName;
        console.log('userName',this.userName);
        this.useravatar = res.data.data.userAvatar;
        // console.log(this.useravatar)
      },err=>{
        console.log(err);
      })
    },
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
    // },
    openTag(id) {
      console.log('openTagId',id);
      this.$router.push({path:'/Tag',query:{tagID:id}});
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
      userName: '',
      useravatar: '',
      // pic: require('../../src/assets/mlogo.png'),
      article_num:'',
      tip:'这里空空如也，快去创作者中心记录你的生活吧~',
      tabledata: [{
          articleId: 1,
          articleHeading: '',
          articleContent: '',
          picture: require('../../src/assets/discover_pic1.png'),
          publishTime: '2021-12-5',
          tag: '',
          isLiked:false,
          like_src:'',
          articleComments: '',
          articleLikes: '',
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
    margin-bottom: 60px;
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
  /*.icon-like {
    width: 13px;
    padding-right:5px;
}*/
  #icon-like {
    /*content: url(../../src/assets/like.png);*/
    width: 13px;
    /*margin-left: 500px;
    margin-right: 5px;*/
    position: absolute;
    right: 150px;
  }
  #likenum{
    position: absolute;
    right: 135px;
  }
  #icon-command {
    content: url(../../src/assets/command.png);
    width: 13px;
    /*margin-left: 20px;
    margin-right: 5px;*/
    position: absolute;
    right: 90px;
  }
  #commandnum{
    position: absolute;
    right: 75px;
  }
  #time {
    margin-left: 30px;
    font-size: 12px;
    flood-color: darkgray;
    margin-bottom: 10px;
  }
  .opentag-btn {
    color: #606266;
    font-size: 12px;
    padding: 0px;
  }

  /*菜单部分*/
  #menu {
    width: 250px;
    text-align:left
  }
  .tac-menu {
    padding: 0px;
    padding-bottom: 10px;
    border-style: solid;
    border-width: 2px;
    border-color: #DBD8D3;
    border-radius: 4px;
  }
  #menutitle{
    margin-top: 10px;
  }
  #menu-title {
    margin-left: 20px;
    font-size: 24px;
  }
  .icon-person {
    content: url(../../src/assets/user_pic.png);
    width: 18px;
    margin-left: 5px;
    margin-right: 10px;
  }
  .icon-follow {
    content: url(../../src/assets/follow_pic.png);
    width: 18px;
    margin-left: 5px;
    margin-right: 10px;
  }
  .icon-fan {
    content: url(../../src/assets/fan_pic.png);
    width: 18px;
    margin-left: 5px;
    margin-right: 10px;
  }
  .icon-setting {
    content: url(../../src/assets/setting_pic.png);
    width: 18px;
    margin-left: 5px;
    margin-right: 10px;
  }
</style>
