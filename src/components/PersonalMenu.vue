<template>
  <div id="menu">
    <el-row class="tac">
      <el-col class="tac-menu">
        <div id="menutitle"><h id="menu-title">个人主页</h></div>
        <hr color=#EFEEEE SIZE=1>
        <el-menu default-active="1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
          <el-menu-item index="1">
            <i class="icon-person"></i>
            <span slot="title">我的主页</span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="icon-follow"></i>
            <span slot="title">我的关注</span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="icon-fan"></i>
            <span slot="title">我的粉丝</span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title">
              <i class="icon-setting"></i>
              <span>账号管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="4-1">账号资料</el-menu-item>
              <el-menu-item index="4-2">账号安全</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      }
    },
  data() {}
}
</script>

<style>
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