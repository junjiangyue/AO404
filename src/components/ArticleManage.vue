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
                <h1> 最新文章</h1>
                <el-row>
                    <el-col :span="12">
                        <el-card class="box-card"
                        style="margin-right:20px">
                            <div class="text_item">
                              <i class="el-icon-user" style="margin-right:10px"></i>
                                <a>总文章数</a>
                                <p>{{new_article}}</p>
                            </div>
                            </el-card>
                    </el-col>
                    <el-col :span="12">
                        <el-card class="box-card" style="margin-right:40px">
                            <div class="text_item">
                              <i class="el-icon-user" style="margin-right:10px"></i>
                                <a>新增文章数</a>
                                <p>{{unaudit_article}}</p>
                            </div>
                            </el-card>
                    </el-col>
                </el-row>
                 <el-card class="box-card" style="margin-top:20px;margin-right:40px">
                            <el-table :data="tableData"  border style="width: 100%;height=100%">
                                <el-table-column prop="userId" label="用户ID"></el-table-column>
                                <el-table-column prop="articleId" label="文章ID"></el-table-column>
                                <el-table-column prop="articleHeading" label="文章内容"></el-table-column>
                                <el-table-column prop="publishTime" label="发布时间"></el-table-column>
                                <el-table-column label="操作">
                                   <template slot-scope="scope">
                                    <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">查看</el-button>
                                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
        activeIndex: '3',
        new_article: '',
        unaudit_article:'',
         tableData: []
      };
    },
    mounted:function(){
      this.$axios({
        method:"post",
        url: 'api/admin/getArticleList',
        }).then(res=>{
            this.tableData = res.data.data.articleList;
            console.log(res);
		},err=>{
			console.log(err);
		}),
    this.$axios({
        method:"post",
        url: 'api/admin/getArticleList',
        }).then(res=>{
            this.new_article = res.data.data.articleList.length;
            console.log("总文章数:",res.data.data.articleList.length);
		},err=>{
			console.log(err);
		}),
    this.$axios({
        method:"get",
        url: 'api/admin/weekNew',
        }).then(res=>{
            this.unaudit_article = res.data.data.weekNew;
            console.log("新增文章数:",res.data.data.weekNew);
		},err=>{
			console.log(err);
		})
    },
    methods: {
      handleEdit(index, row) {
        // 将文章ID传给ArticleInfo页面
        console.log( row.articleId);
        this.$axios({
        method:"get",
        url: 'api/article/articleInfo',
        params:{
          articleId: row.articleId,
        },
        headers:{
         token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log(row.articleId),
          this.$router.push({name:'ArticleAdmin',query:{articleId:row.articleId}});
          console.log(res);
        },err=>{
          console.log(err);
        })

      },
      handleDelete(index, row){
        console.log( row.userId);
        this.$axios({
        method:"delete",
        url: 'api/admin/deleteArticle',
        params:{
          articleId: row.articleId,
        },
        headers:{
         token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          if(res.data.data.msg=="删除成功")
          {
            this.tableData.splice(index, 1);
            }
            console.log(res);
        },err=>{
          console.log(err);
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
      },
      fetchdata() {},
      
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