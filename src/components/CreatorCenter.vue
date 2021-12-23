<template>
  <div class="centerbody">
    <Guidebar></Guidebar>
    <div class="centercontent">
      <div id="centernavigationbar" class="centerview">
        <el-row class="tac" style="width:420px;">
  <el-col :span="12">
    <h5>创作者中心</h5>
    <el-menu
      default-active="1"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="white"
      text-color="#2c3e50"
      active-text-color="#black">
      <el-menu-item index="1">
        <i class="el-icon-notebook-2"></i>
        <span slot="title">创作主页</span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/notice" style="text-decoration:none;color:#2c3e50;">系统通知</router-link></span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-s-order"></i>
        <span slot="title"><router-link to="/advicefeedback" style="text-decoration:none;color:#2c3e50;">意见反馈</router-link></span>
      </el-menu-item>
    </el-menu>
  </el-col>
</el-row>
      </div>
      <div id="centersubstance" class="centerview">
        <div class="centerbox1">
          <h2>立即创作</h2>
          <div class="word" style="margin-left: 24%;"><img id="textpng" @click="jumpword" src="@/assets/posttext.png"><p id="textword">文字</p></div>
          <div class="word"><img id="imgpng" @click="jumpword" src="@/assets/postimg.png"><p style="margin-left:75px;">图片</p></div>
          
        </div>
        <div class="centerbox2">
          <h2>与我相关</h2>
          <div class="owninfro" style="margin-left:-55px;" @click="goFollowlist"> <h3 id="followers">{{ownfollowers}}</h3><p>我的关注</p></div>
          <div class="owninfro"><h3 id="fans" style="margin-left: 176px;" @click="goFanlist">{{ownfans}}</h3><p style="margin-left: 168px;">我的粉丝</p></div>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.centerbody{
  width: 100%;
  height: 609px;
}
.centercontent{
  height: 519px;
  width: 100%;
  background-color: rgb(255, 255, 255);
}
.centerview{
  display: inline-block;
  vertical-align: top;
}
.centerview h2{
  text-align: left;
  margin-left: 20px;
}
#centernavigationbar{
  margin-top:35px;
  margin-left: 20%;
  width: 210px;
  height: 440px;
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
#centernavigationbar .el-menu-item {
  color: black;
  background-color: white;
  font-size: 100%;
  font-weight: 550;
  border-top: 1px solid #bbbbbb70;
}
#centernavigationbar .el-icon-notebook-2:before {
    color: #CF4327;
}
#centernavigationbar .el-icon-document:before {
    color: #CF4327;
}
#centernavigationbar .el-icon-s-order:before {
    color: #CF4327;
}
#centersubstance{
  margin-left: 8px;
  margin-top: 35px;
  width: 700px;
  height: 440px;
  border-radius: 20px;
}
.tac h5{
  font-size:20px;
  text-align: left;
  margin-top: 20px;
  margin-left: 20px;
  margin-bottom: 20px;
}
.centerbox1 p{
  width:100%;
  text-align: center;
}
.centerbox1 .word{
  display: inline-block;
  vertical-align: top;
}
.centerbox1 #textpng{
  margin-left: 0px;
}
.centerbox1 #imgpng{
  margin-left: 150px;
}
.centerbox1{
  margin-top: 0px;
  width:700px;
  height: 225px;
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
.centerbox2 p{
  margin-top: -10px;
  margin-left: 232px;
}
.centerbox2 h3{
  margin-top: -5px;
  margin-left: 240px;
  font-size:40px;
  color: rgba(207, 67, 39, 100);
}
.centerbox2 .owninfro{
  display: inline-block;
  vertical-align: top;
}
.centerbox2{
  margin-top: 15px;
  width:700px;
  height: 200px;
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
</style>
<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'CreatorCenter',
  components: {
    Guidebar
    },
  data () {
    return {
      ownfollowers: '',
      ownfans: '',
      userId: 40400001,
    }
  },
  mounted:function() {
    console.log("hello")
    this.$axios({
      method: "post",
      url: 'http://47.102.194.89:8080/user/getMyRelation',
      params: {},
      headers: { token:window.sessionStorage.getItem("token")}
    }).then(res=>{
      console.log(res);
      console.log(res.data.data.fanList.length);
      this.ownfollowers = res.data.data.followList.length;
      this.ownfans = res.data.data.fanList.length
    })
  },
  methods: {
      goFollowlist(){
          this.$router.push('/FollowList')
      },
      goFanlist(){
          this.$router.push('/FanList')
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      jumpword(){
        this.$router.push('/Postword')
      }
    }
}
</script>