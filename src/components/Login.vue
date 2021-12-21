<template>
<div>
    <div class="background"> 
        <img class="logo" src="@/assets/burgundy-4.png" width=1400px height="130%" alt="" />
    </div>   
    <div class="content">
        <div class="login" style="text-align:center">
            <h1>Hello, Welcome to us!</h1>
            <div class="editview">
                 <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                <el-form-item prop="userid">
                <li><el-input v-model="ruleForm.userid" placeholder="请输入您的ID" ></el-input></li></el-form-item>
                <el-form-item prop="password">
                <li><el-input placeholder="请输入密码" v-model="ruleForm.password" show-password></el-input></li></el-form-item>
                <li><el-button type="primary" round class="btn_login" @click="login">登录</el-button></li>
                <li><el-checkbox v-model="agree">我同意 
                    <el-link href="https://www.lofter.com/agreement" target="_blank" type="warning">服务协议</el-link>和
                    <el-link href="https://www.lofter.com/agreement?op=privacyPolicy" target="_blank" type="warning">隐私政策</el-link>
                    </el-checkbox></li>
                     </el-form>
                </div>

        <div class="find_password">
            <li class="signup"><el-link type="warning" @click="gotosign">注册账号</el-link></li>
            <li> <el-link type="info" @click="goFP">忘记密码</el-link></li>
        </div>

        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'Login',
    data() {
        var checkId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('用户ID不能为空!'));
        }
        };
        var checkPass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空!'));
          }
        };
    return {
      agree: false,
      ruleForm: {
      userid: '',
      password:'',
      },
      rules: {
           userid: [
          {validator: checkId,trigger: "blur"}
          ],
           password: [
          {validator: checkPass,trigger: "blur"}
          ]
        }
    }
  },
  methods:{
    gotosign(){
        this.$router.push('/SignUp')
    },
    goFP(){
        this.$router.push('/ForgetPassword')
    },
    login(){
        if(this.agree == false)
        {
            console.log(this.agree);
            this.$message({
                    showClose: true,
                    message: '请勾选服务协议和隐私政策',
                    type: 'error'
                    });
        }else{
            this.$axios({
            method:"post",
            url: 'api/user/login',
            params:{
                userId:this.ruleForm.userid,
                userPassword:this.ruleForm.password,
            }
            }).then(res=>{
                console.log(res.data.msg);
                if(res.data.msg=="登录成功"){
                    console.log(res);
                    window.sessionStorage.setItem("token",res.data.token);
                    this.$router.push('/Main')
                }
                else{
                 this.$message({
                    showClose: true,
                    message: '登录失败!'
                    });
                }
            },err=>{
                console.log(err);
            })
        }
   }
  }
}
</script>
<style lang="css" scoped>
.background {
    width:100%;
    height:auto;  /**宽高100%是为了图片铺满屏幕 **/
    z-index:-1;
    position: absolute;
    text-align: center;
    background: #EFEEEE;
}
.content{
    z-index:1;
    position: absolute;
    left: 50%;
    top:45%;
    transform: translate(-50%,-50%);
}
.login{
    width:400px;
    height:430px;
    background: #fff;
    border-radius: 20px;
    box-shadow: 1px 1px 1px 1px #e5e5e5;
}
.editview{
    margin:50px 50px;
    margin-top: 20px;
    margin-bottom: 20px;
}
h1{
    padding-top: 50px;
    margin: 0;
    font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
}
>>>.el-input__inner{
    border-radius:20px;
    border-width:1px;
    height: 46px;
    width: 300px;
}
li{
     list-style-type:none;
     padding-top:10px;
}
.btn_login{
    height: 46px;
    width: 300px;
    background-color: #843E30;
    border-width:0px;
}
>>>.el-checkbox__input.is-checked+.el-checkbox__label {
    color: #000;
}
.find_password{
    margin-bottom: 50px;
    margin-right: 50px;
    margin-left: 50px;
    display: flex;
}
.signup{
    padding-right: 180px;
    font-size: 14px;
}
</style>