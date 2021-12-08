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
                  <el-menu default-active="3" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
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
            <div id="fan-list">
              <div id="allfan">全部粉丝（{{follow_num}}个）</div>
              <div>
                <div v-for="(item) in tabledata" :key="item.id">
                  <div>
                    <hr color=#EFEEEE SIZE=1>
                    <p id="fan-user">
                      <img v-bind:src="item.picture" width="55px" align="middle">
                      <span id="fan-name">{{item.user_name}}</span>
                      <el-button v-if="item.follow === 0" round plain type="primary" id="follow">+关注</el-button>
                      <el-button v-else round plain type="info" id="follow">已关注</el-button>
                    </p>
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
  name: 'FanList',
  components: {
    Guidebar
  },
  methods: {
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
    }
  },
  data() {
    return {
      follow_num:20,
      tabledata: [{
          id: 1,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
          follow: 0,
        }, {
          id: 2,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
          follow: 1,
        }, {
          id: 3,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
          follow: 1,
        }, {
          id: 4,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
          follow: 0,
        }, {
          id: 5,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
          follow: 0,
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
  #fan-list {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
  }
  #allfan {
    font-size: 20px;
    margin-top: 15px;
    margin-left: 25px;
    margin-bottom: 10px;
  }
  #fan-user {
    margin-left: 25px;
  }
  #fan-name {
    font-size: 18px;
    margin-left: 10px;
  }
  #follow {
    float: right;
    margin-right: 80px;
  }

  

  /*菜单*/
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