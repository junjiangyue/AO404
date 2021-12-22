<template>
<div>
    <div class="background"> 
        <img class="logo" src="@/assets/burgundy-4.png" width=1400px height="130%" alt="" />
    </div>   
    <div class="content">
        <div class="login" style="text-align:center">
            <h1>Reset Your Password</h1>
            <div class="editview">
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
                     <el-form-item prop="email">
                <li><el-input v-model="ruleForm.email" placeholder="请输入邮箱" width=300px ></el-input></li></el-form-item>
                <el-form-item prop="verification_code">
                <li><el-input placeholder="请输入验证码" v-model="ruleForm.verification_code" class="veri">
                     <el-button slot="append" class="get_veri" @click="verify">获取验证码</el-button></el-input></li></el-form-item>
                     <el-form-item prop="userid">
                <li><el-input v-model="ruleForm.userid" placeholder="请输入您的ID" ></el-input></li></el-form-item>
                <el-form-item prop="password">
                <li><el-input placeholder="请输入新的密码" v-model="ruleForm.password" show-password width=300px></el-input></li></el-form-item>
                <li><el-button type="primary" round class="btn_signup" @click="changePsw">确定</el-button></li>
                </el-form>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'ForgetPassword',
    data() {
    var checkEmail = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('邮箱不能为空!'));
        }
        if (/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/.test(value) == false) {
                return callback(new Error("请输入正确的邮箱地址!"));
              } else {
                //校验通过
                return callback();
              }
        };
        var checkPass = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('密码不能为空!'));
          }
        };
        var checkId = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('ID不能为空!'));
          }
        };
    return {
      vericode:'',
      agree: false,
      ruleForm: {
      email: '',
      password:'',
      verification_code:'',
      userid:'',
      },
      rules: {
            email: [
          {validator: checkEmail,trigger: "blur"}
          ],
           password: [
          {validator: checkPass,trigger: "blur"}
          ],
          userid: [
          {validator: checkId,trigger: "blur"}
          ],
        }
      };
    },
    mounted(){

    },
    methods:{

        verify(){
           this.$axios({
               method:"post",
               url:'http://localhost:8080/mail/mail',
               params:{email:this.ruleForm.email},
           }).then(res=>{
               this.vericode = res.data;
				console.log(res.data);

			},err=>{
				console.log(err);
			})
        },
        changePsw(){
            if(this.ruleForm.verification_code != this.vericode){
                this.$message({
                showClose: true,
                message: '验证码错误!'
                });
            }else {
                this.$axios({
                method:"post",
                url: 'api/user/register',
                params:{
                   userName:this.ruleForm.name,
                   userPassword:this.ruleForm.password,
                   userEmail:this.ruleForm.email
                }
           }).then(res=>{
               if (res.data.msg != "关键数据缺失")
               {
				console.log(res.data);
                this.$router.push('/')
                }
                else{
                this.$message({
                showClose: true,
                message: '缺少关键信息!',
                type: 'error'
                })
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
    height:480px;
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
     padding-top:0px;
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