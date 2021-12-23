<template>
  <div class="feedbackbody">
    <Guidebar></Guidebar>
    <div class="advicecontent">
      <div id="advicenavigationbar" class="feedbackcontent">
        <el-row class="tace" style="width:420px;">
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
        <span slot="title"><router-link to="/creatorcenter" style="text-decoration:none;color:#2c3e50;">主页</router-link></span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-document"></i>
        <span slot="title"><router-link to="/notice" style="text-decoration:none;color:#2c3e50;">通知</router-link></span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-s-order"></i>
        <span slot="title">意见反馈</span>
      </el-menu-item>
    </el-menu>
  </el-col>
</el-row>
      </div>
      <div id="feedbackview" class="feedbackcontent">
        <div class="feedbackbox">
          <img src="@/assets/feedback.png" id="feedbackimg">
          <div class="inputbord">
              <el-input id="inputmessage" type="textarea" :rows="2" placeholder="请输入内容" v-model="textarea"></el-input>
          </div>
          <h3>联系方式：</h3>
          <div class="contectbord">
              <el-input id="contectinput" v-model="input" placeholder="请输入联系方式"></el-input>
          </div>
          <el-button @click="commit">提交</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<style>
.feedbackbody{
  width: 100%;
  height: 609px;
}
.advicecontent{
  height: 519px;
  width: 100%;
  background-color: rgb(255, 255, 255);
}
.feedbackbox .inputbord{
    height: 160px;
    width: 100%;
    margin-left:33px;
    margin-top: 10px;
}
.feedbackbox .inputbord #inputmessage{
    border: 1px solid rgba(187, 187, 187, 100);
    height: 160px;
    width: 90%;
    border-radius: 10px;
}
.feedbackbox h3{
    text-align: left;
    margin-left: 33px;;
}
.feedbackbox .contectbord{
    border-radius: 10px;
    width: 100%;
    height: 30px;
}
.feedbackbox .contectbord #contectinput{
    border: 1px solid rgba(187, 187, 187, 100);
    border-radius: 10px;
    width: 90%;
    margin-left: 30px;
    height: 50px;
}
.feedbackbox .el-button{
  margin-left: 45%;
    margin-top: 30px;
}
#feedbackimg{
    width: 350px;
    height: 40px;
    margin-left: 30px;
    margin-top: 10px;
}
.feedbackcontent{
  display: inline-block;
  vertical-align: top;
}
.feedbackcontent h2{
  text-align: left;
  margin-left: 20px;
}
#advicenavigationbar{
  margin-left: 20%;
  margin-top:35px;
  width: 210px;
  height: 440px;
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
#advicenavigationbar .el-menu-item {
  color: black;
  background-color: white;
  font-size: 100%;
  font-weight: 550;
  border-top: 1px solid #bbbbbb70;
}
#advicenavigationbar .el-icon-notebook-2:before {
    color: #CF4327;
}
#advicenavigationbar .el-icon-document:before {
    color: #CF4327;
}
#advicenavigationbar .el-icon-s-order:before {
    color: #CF4327;
}
#feedbackview{
  margin-left: 8px;
  margin-top: 35px;
  width: 700px;
  height: 440px;
  border-radius: 20px;
}
.tace h5{
  font-size:20px;
  text-align: left;
  margin-top: 20px;
  margin-left: 20px;
  margin-bottom: 20px;
}
.feedbackbox p{
  width:100%;
}
.feedbackbox .word{
  display: inline-block;
  vertical-align: top;
}
.feedbackbox #textpng{
  margin-left: 0px;
}
.feedbackbox #imgpng{
  margin-left: 220px;
}
.feedbackbox{
  margin-top: 0px;
  width:700px;
  height: 440px;
  background-color: rgb(255, 255, 255);
  border-radius: 4px;
  border: 1px solid #EBEEF5;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
}
</style>
<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'AdviceFeedback',
  components: {
    Guidebar
    },
  data () {
    return {
      ownfollowers: '12',
      ownfans: '23',
      textarea: '',
      input: '',
      userId: 40400001,
    }
  },
  methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      commit() {
        console.log(this.textarea);
        if(this.textarea!=''&&this.input!=''){
        this.$axios({
          method:"post",
          url: 'http://47.102.194.89:8080/feedback/feedback',
          params: {
            feedbackContent: this.textarea,
          },
          headers: { token:window.sessionStorage.getItem("token")},
        }).then(res=>{
          console.log(res);
          if(res.data.data.msg == "Success") {
            this.$notify({
          title: '成功',
          message: '反馈成功，感谢您的使用！',
          type: 'success'
        });
          }
        })}
        else {
          this.$notify({
          title: '警告',
          message: '输入不可以为空',
          type: 'warning'
        });
        }
      },
    }
}
</script>