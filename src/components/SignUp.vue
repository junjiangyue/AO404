<template>
<div>
    <div class="background"> 
        <img class="logo" src="@/assets/burgundy-4.png" width=1400px height="130%" alt="" />
    </div>   
    <div class="content">
        <div class="login">
            <h1>Hello, Welcome to us!</h1>
            <div class="editview">
                <li><el-input v-model="email" placeholder="请输入邮箱" width=300px></el-input></li>
                <li><el-input placeholder="请输入密码" v-model="password" show-password width=300px></el-input></li>
                <li><el-input placeholder="请输入验证码" v-model="verification_code" class="veri">
                     <el-button slot="append" class="get_veri">获取验证码</el-button></el-input></li>
                <li><el-button type="primary" round class="btn_signup">注册</el-button></li>
                <li><el-checkbox v-model="agree">我同意 
                    <el-link href="https://www.lofter.com/agreement" target="_blank" type="warning">服务协议</el-link>和
                    <el-link href="https://www.lofter.com/agreement?op=privacyPolicy" target="_blank" type="warning">隐私政策</el-link>
                    </el-checkbox></li>
                </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'SignUp',
    data() {
    return {
      emial: '',
      password:'',
      agree: false
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
    
}
>>>.veri .el-input__inner{
    border-radius:20px 0px 0px 20px;
    border-width:1px;
    height: 46px;
    
}
li{
     list-style-type:none;
     padding-top:20px;
}
.btn_signup{
    height: 46px;
    width: 300px;
    background-color: #843E30;
    border-width:0px;
}
>>>.el-checkbox__input.is-checked+.el-checkbox__label {
    color: #000;
}
.get_veri{
    width: 100px;
}
>>>.el-input-group__append{
    border-radius:0px 20px 20px 0px;
    background-color: #EFEEEE;
}
</style>