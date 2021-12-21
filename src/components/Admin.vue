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
                <el-input type="search" class="searchbox" placeholder="请输入内容" prefix-icon="el-icon-search" v-model="input2"></el-input>
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
                                <a>本周新增用户</a><p>{{new_user}}</p>
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
        activeIndex: '1',
        user_number: 10000,
        article_number:5000,
        new_user:200,
      };
    },
    methods: {
      myEcharts(){
		  // 基于准备好的dom，初始化echarts实例
		  var myChart = this.$echarts.init(document.getElementById('main'));

		  // 指定图表的配置项和数据
		  var option = {
			  title: {
				  text: 'ECharts 入门示例'
			  },
			  tooltip: {},
			  legend: {
				  data:['销量']
			  },
			  xAxis: {
				  data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
			  },
			  yAxis: {},
			  series: [{
				  name: '销量',
				  type: 'bar',
				  data: [5, 20, 36, 10, 10, 20]
			  }]
		  };

		  // 使用刚指定的配置项和数据显示图表。
		  myChart.setOption(option);
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
  	this.myEcharts();
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
