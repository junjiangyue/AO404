<template>
<div >
    <el-row>
        <el-col :span="12"  class="background">
        <div class="illu">
            <img class="adminlogin" src="@/assets/adminlogin.png" width="80%" height="80%">
        </div>
        </el-col>
    <el-col :span="8">
        <div class="log_block">
            <div class="login">
                <h1>Adminnistrater Log In</h1>
                <div class="editview">
                    <li><el-input v-model="userid" placeholder="请输入ID" style="width: 400px;"></el-input></li>
                    <li><el-input placeholder="请输入密码" v-model="password" show-password style="width: 400px;"></el-input></li>
                    <li><el-button type="primary" round class="btn_login" @click="gotoAdmin">登录</el-button></li>
                    </div>
                </div>
            </div>
        </el-col>
    </el-row>
</div>
</template>
<script>
export default {
    data() {
    return {
      userid: '',
      password:'',
    }
  },
    methods:{
    gotoAdmin(){
        this.$axios({
        method:"post",
        url: 'api/user/login',
        params:{
            userId:this.userid,
            userPassword:this.password,
        }
        }).then(res=>{
			console.log(res.data.msg);
            if(res.data.msg=="用户不存在"){
                this.$message({
                showClose: true,
                message: '用户不存在!'
                });
            }
            else{
            console.log(res);
            window.sessionStorage.setItem("token",res.data.token);
            this.$router.push('/Admin')
            }
		},err=>{
			console.log(err);
		})
    }
  }
}
</script>

<style scoped>
.log_block{
    margin-top:100px;
    margin-left:200px;
}
.illu{
    height:660px;
    text-align: center;
    margin-top: 100px;
}
.background{
    background-color: #8F5255;
}
.login{
    height:430px;
    background: #fff;
}
.editview{
    margin-top: 50px;
    margin-bottom: 50px;
}
h1{
    padding-top: 50px;
    margin: 0;
    font-size:40px;
    font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif
}

>>>.el-input__inner{
    border-radius:20px;
    border-width:0px;
    height: 46px;
    background-color: #EFEEEE;
}
li{
     list-style-type:none;
     padding-top:30px;
}
.btn_login{
    height: 46px;
    width: 400px;
    background-color: #843E30;
    border-width:0px;
}
</style>