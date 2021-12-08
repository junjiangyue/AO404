<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div id="page-content">
      <el-row>
        <el-col :span="6"><div class="grid-content"><PersonalMenu></PersonalMenu></div></el-col>
        <el-col :span="18">
          <div class="grid-content">
            <div id="usercard">
              <div><img src="../../src/assets/personalpage.png" width="100%" class="bg-purple-light"></div>
              <div id="user-card"><div id="user-info">
                <p>
                  <img v-bind:src="pic" width="70px" align="middle">
                  <span id="bigname">{{tag_name}}</span>
                </p>
              </div></div>
            </div>
            <div class="txt"><p >全部帖子（{{article_num}}个）</p></div>
            <div v-for="(item) in tabledata" :key="item.id">
              <div id="article-card">
                <p id="user-head">
                  <img v-bind:src="pic" width="50px" align="middle">
                  <span>{{tag_name}}</span>
                </p>
                <p id="title">{{item.title}}</p>
                <hr align=center color=#EFEEEE SIZE=1 width="95%">
                <p id="content">{{item.content}}</p>
                <img v-bind:src="item.picture" width="100px" id="picture">
                <div>
                  <p id="time">{{item.time}}&emsp;|&emsp;{{item.tag}}
                    <i class="icon-like"></i>
                    <span>{{item.like_num}}</span>
                    <i class="icon-command"></i>
                    <span>{{item.command_num}}</span>
                  </p>
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
import PersonalMenu from '@/components/PersonalMenu'
export default {
  name: 'PersonalPage',
  components: {
    Guidebar,
    PersonalMenu
  },
  data() {
    return {
      tag_name: '原神',
      pic: require('../../src/assets/mlogo.png'),
      article_num:20,
      tabledata: [{
          id: 1,
          title: '原神',
          content: '哈哈哈',
          picture: require('../../src/assets/discover_pic1.png'),
          time: '2021-12-5',
          tag: '#原神',
          command_num: 100,
          like_num: 500
        }, {
          id: 2,
          title: '原神',
          content: '哈哈哈',
          picture: null,
          time: '2021-12-5',
          tag: '#原神',
          command_num: 100,
          like_num: 500
        }, {
          id: 3,
          title: '原神',
          content: null,
          picture: require('../../src/assets/discover_pic1.png'),
          time: '2021-12-5',
          tag: '#原神',
          command_num: 100,
          like_num: 500
        }, {
          id: 4,
          title: '原神',
          content: '哈哈哈',
          picture: require('../../src/assets/discover_pic1.png'),
          time: '2021-12-5',
          tag: '#原神',
          command_num: 100,
          like_num: 500
        }, {
          id: 5,
          title: '原神',
          content: '哈哈哈',
          picture: require('../../src/assets/discover_pic1.png'),
          time: '2021-12-5',
          tag: '#原神',
          command_num: 100,
          like_num: 500
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
  #user-info {
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
