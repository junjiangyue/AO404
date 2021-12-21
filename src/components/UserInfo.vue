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
                  <el-menu default-active="4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
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
            <div id="user-info">
              <div id="info">账号资料</div>
              <hr color=#EFEEEE SIZE=1>
              <div id="photoblock"><p>
                <img v-bind:src="pic" id="headphoto" @click="headDialogVisible = true">
                <el-dialog title="修改头像" :visible.sync="headDialogVisible" width="25%" center append-to-body>
                  <span>
                    <el-upload
                      class="avatar-uploader"
                      action="/api/picture/uploadAvatar"
                      :headers="headers"
                      :show-file-list="false"
                      :on-success="handleAvatarSuccess">
                      <img v-if="imageUrl" :src="imageUrl" class="avatar">
                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                  </span>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="headDialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="postAvatar()">确 定</el-button>
                  </span>
                </el-dialog>
                <!--<el-button @click="uploadAvatar">修改头像</el-button>
                <input type="file" accept="image/*" style="display:none" @change="handleFile" class="hiddenInput"/>-->
              </p>
 
              </div>
              <div id="userinfo">
                <p>账&ensp;号&ensp;ID：{{userId}}</p>
                <p>昵&emsp;&emsp;称：{{userName}}</p>
                <p>关联邮箱：{{userEmail}}</p>
                <el-button id="changeinfo" @click="infoDialogVisible = true">修改资料</el-button>
                <el-dialog title="修改资料" :visible.sync="infoDialogVisible" width="40%" center>
                  <div id="input-info">
                    <p>昵&emsp;&emsp;称：<el-input  placeholder="请输入内容"  v-model="input_name"  clearable style="width: 300px"></el-input></p>
                    <p>关联邮箱：<el-input  placeholder="请输入内容"  v-model="input_email"  clearable style="width: 300px"></el-input></p>
                  </div>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="infoDialogVisible = false">取消修改</el-button>
                    <el-button type="primary" @click="confirmDialogVisible = true">提交修改</el-button>
                    <el-dialog title="提示" :visible.sync="confirmDialogVisible" width="25%" center append-to-body>
                      <span>确认提交修改？</span>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="confirmDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="changeInfo">确 定</el-button>
                      </span>
                    </el-dialog>
                  </span>
                </el-dialog>
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
  name: 'UserInfo',
  components: {
    Guidebar
  },
  data() {
    return {
      userId:'',
      userName: '',
      pic: require('../../src/assets/mlogo.png'),
      userEmail: '',
      input_name: '哈哈哈',
      input_email:'123456@qq.com',
      headDialogVisible: false,
      infoDialogVisible: false,
      confirmDialogVisible: false,
      imageUrl: '',
      headers:{
        'token':window.sessionStorage.getItem("token")
      }
    }
  },
  mounted:function(){
    this.getMyInfo(),
    this.getAvatar()
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
    },
    changeInfo() {
      console.log(this.input_name,this.input_email),
      this.infoDialogVisible=false,
      this.confirmDialogVisible=false,
      this.$message({
          message: '信息修改成功！',
          type: 'success'
        });
    },
    getMyInfo() {
      this.$axios({
        method:"get",
        url: 'api/user/myInfo',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的信息数据：', res.data);
        this.userId=res.data.data.userId;
        console.log('userId',this.userId);
        this.userName=res.data.data.userName;
        console.log('userName',this.userName);
        this.userEmail=res.data.data.userEmail;
        console.log('userEmail',this.userEmail);
        //this.tabledata=res.data.data.tagInArticle;
      },err=>{
        console.log(err);
      })
    },
    getAvatar() {
      this.$axios({
        method:"post",
        url: 'api/picture/getAvatar',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的头像数据：', res.data);
      },err=>{
        console.log(err);
      })
    },
    postAvatar() {
      this.$axios({
        method:"post",
        url: 'api/picture/uploadAvatar',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的头像数据：', res.data);
        
      },err=>{
        console.log(err);
      })
    },
    uploadAvatar:function(){
      this.$el.querySelector('.hiddenInput').click()
    },
    handleFile:function(e){
      let $target=e.target || e.srcElement
      let file = $target.files[0]
      var reader = new FileReader()
      reader.onload=(data) => {
        let res = data.target || data.srcElement
        this.pic=res.result
      }
      reader.readAsDataURL(file)
    },
    handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
        console.log("上传图片",res)
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
  #user-info {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
  }
  #info {
    font-size: 20px;
    margin-top: 15px;
    margin-left: 25px;
    margin-bottom: 10px;
  }
  #photoblock{
    text-align:center;
  }
  #headphoto {
    width: 120px;
  }
  #userinfo {
    margin-left: 300px;
    font-size: 18px;
    margin-bottom: 40px;
  }
  #changeinfo {
    margin-left: 100px;
    margin-top: 20px;
  }
  #input-info {
    text-align:center;
    font-size: 16px;
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
    .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 300px;
    line-height: 300px;
    text-align: center;
  }
  .avatar {
    width: 300px;
    display: block;
  }
</style>