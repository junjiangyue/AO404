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
                    <img v-bind:src="pic" width="70px" align="middle">
                    <span id="bigname">{{userName}}</span>
                  </p>
                </div>
              </div>
            </div>
            <div class="txt"><p >全部帖子（{{article_num}}个）</p></div>
            <div v-for="(item) in tabledata" :key="item.id">
              <div id="article-card">
                <p id="user-head">
                  <img v-bind:src="pic" width="50px" align="middle">
                  <span>{{userName}}</span>
                </p>
                <p id="title">{{item.articleHeading}}</p>
                <hr align=center color=#EFEEEE SIZE=1 width="95%">
                <p id="content">{{item.articleContent}}</p>
                <img v-bind:src="item.picture" width="100px" id="picture">
                <div>
                  <p id="time">{{item.publishTime}}&emsp;|&emsp;{{item.tag}}
                    <i class="icon-like"></i>
                    <span>{{item.articleLikes}}</span>
                    <i class="icon-command"></i>
                    <span>{{item.articleComments}}</span>
                  </p>
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
  },
  methods: {
    
  },
  data() {
    return {
      userName: '',
      pic: require('../../src/assets/mlogo.png'),
      article_num:'',
      tabledata: [{
          articleId: 1,
          articleHeading: '',
          articleContent: '',
          picture: require('../../src/assets/discover_pic1.png'),
          publishTime: '2021-12-5',
          tag: '',
          articleComments: '',
          articleLikes: ''
        }]
    }
  }
}
</script>

<style>
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
  .icon-like {
    content: url(../../src/assets/like.png);
    width: 13px;
    margin-left: 600px;
    margin-right: 5px;
  }
  .icon-command {
    content: url(../../src/assets/command.png);
    width: 13px;
    margin-left: 20px;
    margin-right: 5px;
  }
  #time {
    margin-left: 30px;
    font-size: 12px;
    flood-color: darkgray;
  }
</style>