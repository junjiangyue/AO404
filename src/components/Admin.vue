<template>
    <el-container>
        <el-aside width="300px">
            <el-menu
        :default-active="activeIndex"
        active-text-color="#8F5255"
        class="el-menu-vertical"
        @select="handleSelect">
        <img class="logo" src="@/assets/logo.png">
      <el-menu-item index="1" style="margin-top:30px">
        <i class="el-icon-setting"></i>
        <span slot="title" @click="gotoAdmin">管理中心</span>
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
            <el-main class="main" >
                <h1> 管理中心</h1>
                <el-row>
                    <el-col :span="8">
                        <el-card class="box-card"
                        style="margin-right:20px">
                            <div class="text_item">
                              <i class="el-icon-user" style="margin-right:10px"></i>
                                <a>总用户数</a>
                                <p>{{user_number}}</p>
                            </div>
                            </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card"
                         style="margin-right:20px">
                            <div class="text_item">
                              <i class="el-icon-edit" style="margin-right:10px"></i>
                               <a>总文章数</a>
                               <p>{{article_number}}</p>
                            </div>
                            </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card class="box-card" style="margin-right:40px">
                            <div  class="text_item">
                              <i class="el-icon-data-analysis" style="margin-right:10px"></i>
                                <a>本周新增文章</a><p>{{new_user}}</p>
                            </div>
                            </el-card>
                    </el-col>
                </el-row>
                 <el-card class="box-card" style="margin-top:20px;margin-right:40px" >
                   <div class="Echarts" style="width: 600px;height:400px;" id="main"></div>
                            </el-card>
                            
                </el-main>
        </el-container>
    </el-container>

</template>
<script>
export default({
  name: 'Echarts',
    data() {
      return {
        input2:'',
        activeIndex: '1',
        user_number: '',
        article_number:'',
        new_user:'',
        newarticle:'',
        option: {
			  title: {
				  text: '一周文章数分析'
			  },
			  tooltip: {},
			  legend: {
				  data:['文章数']
			  },
			  xAxis: {
				  data: ["周一","周二","周三","周四","周五","周六","周日"]
			  },
			  yAxis: {},
			  series: [{
				  name: '文章数',
				  type: 'bar',
				  data: [1,3,5,6,0,0,0]
			  }]
		  }
      };
    },
    methods: {
      myEcharts(){
		  // 基于准备好的dom，初始化echarts实例
		  var myChart = this.$echarts.init(document.getElementById('main'));
		  myChart.setOption(this.option);
		  },
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
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

    },
    mounted() {
  	
    this.$axios({
        method:"post",
        url: 'api/admin/getUserList',
        }).then(res=>{
            this.user_number = res.data.data.userList.length;
            console.log("用户总数:",res.data.data.userList.length);
		},err=>{
			console.log(err);
		}),
    this.$axios({
        method:"post",
        url: 'api/admin/getArticleList',
        }).then(res=>{
            this.article_number = res.data.data.articleList.length;
            console.log("总文章数:",res.data.data.articleList.length);
		},err=>{
			console.log(err);
		}),
    this.$axios({
        method:"get",
        url: 'api/admin/weekNew',
        }).then(res=>{
            this.new_user = res.data.data.weekNew;
            this.newarticle = res.data.data.weekNew - 15;
            this.option.series[0].data[4]= this.newarticle;
            console.log( this.option.series[0].data[4]);
            console.log("新增文章数:",res.data.data.weekNew);
            this.myEcharts();
		},err=>{
			console.log(err);
		})
    
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
