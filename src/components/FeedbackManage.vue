<template>
    <el-container>
        <el-aside width="300px">
            <el-menu
        :default-active="activeIndex"
        active-text-color="#8F5255"
        class="el-menu-vertical"
        @select="handleSelect">
      <!-- @open="handleOpen"
      @close="handleClose"> -->
      <img class="logo" src="@/assets/logo.png">
      <el-menu-item index="1" style="margin-top:30px">
        <i class="el-icon-setting"></i>
        <span slot="title"  @click="gotoAdmin">管理中心</span>
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
                </el-header>
            <el-main class="main">
                <h1> 反馈列表</h1>
                <el-row>
                    <el-col >
                        <el-card class="box-card"
                        style="margin-right:20px">
                             <div class="text_item">
                              <i class="el-icon-user" style="margin-right:10px"></i>
                                <a>新增反馈数</a>
                                <p>{{new_feedback}}</p>
                            </div>
                            </el-card>
                    </el-col>
                </el-row>
                 <el-card class="box-card" style="margin-top:20px;margin-right:40px">
                            <el-table
                                :data="tableData"
                                border
                                style="width: 100%;height:100%">
                                <el-table-column
                                  prop="feedbackId"
                                  label="反馈编号"
                                  width="180">
                                </el-table-column>
                                <el-table-column
                                  prop="feedbackTime"
                                  label="反馈时间"
                                  width="180">
                                </el-table-column>
                                <el-table-column
                                  prop="userName"
                                  label="用户名">
                                </el-table-column>
                                <el-table-column
                                  prop="userId"
                                  label="用户ID">
                                </el-table-column>
                                <el-table-column
                                  prop="feedbackContent"
                                  label="反馈内容">
                                </el-table-column>
                                <el-table-column label="操作">
                                   <template slot-scope="scope">
                                    <el-button
                                      size="mini"
                                      @click="handleEdit(scope.$index, scope.row)">回复</el-button>
                                  </template>
                                </el-table-column>
                              </el-table>
                            </el-card>
                </el-main>
        </el-container>
    </el-container>

</template>
<script>
export default({
    data() {
      return {
        activeIndex: '4',
        new_feedback:'',
        tableData:[],
        message:'',
      };
    },
    mounted: function(){
        this.$axios({
        method:"get",
        url: 'api/feedback/getfeedback',
        headers:{
          token:window.sessionStorage.getItem("token")}
        }).then(res=>{
            this.tableData = res.data.data.feedbackList;
            this.new_feedback = res.data.data.feedbackList.length;
            console.log(res);
      },err=>{
        console.log(err);
      })
    },
    methods: {
      handleEdit(index,row){
        this.$prompt('请输入', '编辑', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          if (value == null){
              this.$message({
                showClose: true,
                message: '不能为空!',
                type: 'error'})
          }
          else {
          console.log(value,row.feedbackId),
          this.$axios({
            method:"post",
            url: 'api/feedback/answerfeedback',
            // headers:{
            // token:window.sessionStorage.getItem("token")},
            params:{
                feedbackId:row.feedbackId,
                message:value,
                messageHead:value,
            }
            
            }).then(res=>{
                console.log(res);
                this.tableData.splice(index, 1);
        },err=>{
          console.log(err);
        })
          }
        })
      },
      gotoAdmin(){
        this.$router.push('/Admin')
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
.logo{
  height:60%;
  width:60%;
}
.text_item{
  text-align:center;
  margin-top:40px;
}
a{
  font-size:14px;
}
p{
  margin-top:20px;
  font-size:42px;
  color:#1A1B1C;
  font-family: "mp-quote", -apple-system-font, BlinkMacSystemFont, "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei UI", "Microsoft YaHei", Arial, sans-serif;
}
.searchbox{
    margin-top:10px;
}
.main{
    background-color: #f6f7f8;
    padding-left:40px;
}
.el-aside {
    padding-left:30px;
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
.el-menu-item {
    font-size: 16px;
    }
</style>