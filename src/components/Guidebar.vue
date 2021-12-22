<template>
<div class="guidebar">
<div class="grid-content">
    <img v-if="useravatar!='data:image/jpeg;base64,'" style="border-radius: 50%;" class="logo" :src="useravatar"/>
    <img v-else style="border-radius: 50%;" class="logo" src="@/assets/mlogo.png"/>
    </div>
  <li class="grid-content">
    <el-button type="text" @click="gotoMain" class="guide">首页</el-button></li>
  <li class="grid-content">
    <el-button type="text" @click="gotoDiscover" class="guide">发现</el-button></li>
  <li class="grid-content">
    <el-button type="text" @click="gotoCreatorCenter" class="guide">创作者中心</el-button></li>
  <div class="search">
    <el-input @keyup.enter.native="searchTagAndUser" type="search" class="searchbox" placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input2">
  </el-input>
      </div>
    <div >
       <el-tooltip class="item" effect="dark" content="通知" placement="bottom" >
      <el-button icon="el-icon-message" circle class="message"  @click="gotoNotice" style="color: #843E30;"></el-button>
       </el-tooltip>
       <el-tooltip class="item" effect="dark" content="个人中心" placement="bottom">
      <el-button icon="el-icon-s-custom" circle class="home" @click="gotoPersonalPage" style="color: #843E30;"></el-button>
      </el-tooltip>
    </div>
</div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1',
        input2:'',
        useravatar: 'https://s2.loli.net/2021/12/23/RoJmvVPN57ecbLk.png'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      gotoMain(){
        this.$router.push('/Main')
      },
      gotoDiscover(){
        this.$router.push('/Discover')
      },
      gotoCreatorCenter(){
        this.$router.push('/CreatorCenter')
      },
      gotoPersonalPage(){
        this.$router.push('/PersonalPage')
      },
      gotoNotice(){
        this.$router.push('/Notice')
      },
      searchTagAndUser() {
        console.log("开始搜索")
        this.$emit('searchEvent',this.input2)
        
        // toMenu (item) {
          if (this.$route.path !== '/SearchResult') {
          // this.$router.push({ path: '/SearchResult' })
          this.$router.push({name:'SearchResult',query:{searchcontent:this.input2}});
           }
        // }


        // this.$router.push({name:'SearchResult',params:{searchcontent:this.input2}});
      },
      arrayBufferToBase64(buffer) {
                  var binary = '';
    var bytes = new Uint8Array( buffer );
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return window.btoa( binary );
            //   var binary = ''
            //   var bytes = new Uint8Array(buffer)
            //   var len = bytes.byteLength
            //   for(var i = 0; i < len; i++) {
            //       binary += String.fromCharCode(bytes[i])
            //   }
            //   return window.btoa(binary)
          }
    },
    mounted:function() {
      this.$axios({
            method:"post",
            url:'http://47.102.194.89:8080/picture/getAvatar',
            responseType: 'arraybuffer',
            headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
            console.log(res)
            console.log(res.data)
        this.useravatar = 'data:image/jpeg;base64,'+this.arrayBufferToBase64(res.data)
        // console.log(this.useravatar)
        })
    }
  }
</script>
<style lang="css" scoped>
.guide {
    color: #fff;
  }
   li {
    padding:30px 0;
    color: #fff;
    list-style-type:none;
    text-align:center;
    padding-right:40px;
    font-size:16px;
  }
  .logo{
    width:50px;
    height:50px;
    padding: 20px 0;
    margin-left: 250px;
    margin-right: 40px;
    /* padding-right:40px;
    padding-left:250px; */
    /* border-radius: 50%; */
    
  }
  .guidebar{
      background: #843E30;
      display: flex;
      text-align: center;
      height: 90px;
      min-width: 1500px;
  }
  .search{
      padding-top: 25px;
  }
.searchbox{
    border-radius:40px;
    width:200px;
    height:30px;
    border-width:0px;
    font-size: 16px;
    margin-left:300px
}
>>>.el-input__inner{
    border-radius:20px;
    border-width:0px;
}
.message{
  margin-top:25px;
  margin-left:20px;
    /* width:30px;
    height:30px; */
}
</style>