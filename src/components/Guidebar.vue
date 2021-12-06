<template>
<div class="guidebar">
<div class="grid-content">
    <img class="logo" src="@/assets/mlogo.png">
    </div>
  <li class="grid-content" @click="gotoMain">首页</li>
  <li class="grid-content" @click="gotoDiscover">发现</li>
  <li class="grid-content" @click="gotoCreatorCenter">创作者中心</li>
  <div class="search">
    <el-input type="search" class="searchbox" placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input2">
  </el-input>
      </div>
    <div >
    <img class="message" src="@/assets/message.png">
    <img class="home" src="@/assets/home.png">
    </div>
</div>
</template>
<script>
  export default {
    data() {
      return {
        activeIndex: '1',
        activeIndex2: '1'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      gotoMain(){
        this.$router.push('/')
      },
      gotoDiscover(){
        this.$router.push('/Discover')
      },
      gotoCreatorCenter(){
        this.$router.push('/CreatorCenter')
      }
    }
  }
</script>
<style lang="css" scoped>
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
    padding-right:40px;
    padding-left:250px
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
    padding-top: 30px;
    padding-left:40px;
    width:30px;
    height:30px;
}
.home{
    padding-top: 30px;
    width:30px;
    height:30px;
    padding-left:40px;
}
</style>