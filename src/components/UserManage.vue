<template>
    <el-container>
        <el-aside width="200px">
            <el-menu
        :default-active="activeIndex"
        active-text-color="#8F5255"
        class="el-menu-vertical"
        @select="handleSelect">
      <!-- @open="handleOpen"
      @close="handleClose"> -->
      <el-menu-item index="1" style="margin-top:120px">
        <i class="el-icon-setting"></i>
        <span slot="title" @click="gotoAdmin" >管理中心</span>
        </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-setting"></i>
        <span slot="title" @click="gotoUserM">用户管理</span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-setting"></i>
        <span slot="title" @click="gotoAticleM">文章审核</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-setting"></i>
        <span slot="title" @click="gotoFeedbackM">反馈处理</span>
      </el-menu-item>
    </el-menu>
        </el-aside>
        <el-container>
            <el-header>
                <el-input type="search" class="searchbox" placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input2"></el-input>
                </el-header>
            <el-main class="main">
                <h1> 用户列表</h1>
                <el-row>
                    <el-col :span="8">
                        <el-card class="box-card"
                        style="margin-right:20px">
                            <div v-for="o in 4" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div>
                            </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card"
                         style="margin-right:20px">
                            <div v-for="o in 4" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div>
                            </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card">
                            <div v-for="o in 4" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div>
                            </el-card>
                    </el-col>
                </el-row>
                 <el-card class="box-card" style="margin-top:20px">
                            <div v-for="o in 4" :key="o" class="text item">
                                {{'列表内容 ' + o }}
                            </div>
                            </el-card>
                </el-main>
        </el-container>
    </el-container>

</template>
<script>
export default({
    data() {
      return {
        activeIndex: '2',
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      gotoAdmin(){
        this.$router.push('/')
      },
      gotoUserM(){
        this.$router.push('/UserManage')
      },
      gotoAticleM(){
        this.$router.push('/ArticleManage')
      },
      gotoFeedbackM(){
        this.$router.push('/FeedbackManage')
      }
    }
})
</script>
<style scoped>
.searchbox{
    margin-top:10px;
}
.main{
    background-color: #EFEEEE;
}
.el-aside {
    min-height: 750px;
    border:1px;
}
.el-menu {
    border-right: solid 0px #e6e6e6;
}
.el-menu-item:focus, .el-menu-item:hover {
    outline:0;
    border-right: solid 3px #8F5255;
    background-color: #f0e2e3;
}
</style>