<template>
  <div class="hello">
    <Guidebar></Guidebar>
      <div class="page_content">
        <el-row>
          <el-col :span="16">
            <div class="article_list" v-for="(item) in tabledata" :key="item.id">
            <el-card class="box-card">
                <div  class="clearfix">
                  <el-row>
                    <el-col :span="3">
                      <img class="writer_avatar" src="@/assets/mlogo.png"/></el-col>
                      <el-col :span="6" style="padding-top: 10px">
                        <div class="writer_name">{{item.userId}}</div>
                        <div style="font-size:12px;color:#939498">发布了动态</div></el-col>
                  </el-row>
                </div>
                <div class="content">
                    <div class="title">{{item.articleHeading}}</div>
                    <div class="time">{{item.publishTime}}</div>
                    <div class="article_content">
                      <el-row>
                        <el-col :span="16">
                      {{item.articleContent}}</el-col>
                        <el-col :span="4" style="padding-top: 10px">
                        <img class="picture" v-bind:src="item.picture" width="100%" height="100%"/></el-col>
                        </el-row>
                    </div>
                    <el-divider></el-divider>
                    <el-row>
                      <el-col :span="16">
                    <div class="tag">#{{item.tagId}}</div></el-col>
                    <el-col :span="6">
                        <i class="icon-like"></i><span style="margin-right:10px;">{{item.articleLikes}}</span>
                        <i class="icon-command"></i><span>{{item.articleComments}}</span>
                    </el-col>
                    </el-row>
                </div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="4">
            <div >
              <el-card class="upload" shadow="never" style="border:0px">
                <img  src="@/assets/arabica-1300.png" width="100%" height="100%" />
                <div style="text-align:center;font-size:14px;">在这里发布你的任何想法！</div>
                <el-row style="text-align:center;padding-left:35px;padding-top:20px">
                  <el-col span="10">
                      <img @click="jumppoatword" src="@/assets/posttext.png" width="50px" height="50px" />
                      <p style="margin:5px">文字</p>
                  </el-col>
                  <el-col span="10">
                      <img @click="jumppoatword"  src="@/assets/postimg.png" width="50px" height="50px" />
                      <p style="margin:5px">图片</p>
                  </el-col>
                </el-row>
              </el-card>
            </div>
          </el-col>
        </el-row>
      </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'Main',
  components: {
    Guidebar
    },
  data(){
    return {
      tabledata: []
    }
  },
  methods:{
    jumppoatword() {
      this.$router.push('/Postword')
    }
  },
  mounted: function(){
    this.$axios({
    method:"get",
    url: 'api/article/homeArticle',
    headers:{
    token:window.sessionStorage.getItem("token")}
    }).then(res=>{
		console.log(res);
    this.tabledata = res.data.data.homeList;
		},err=>{
			console.log(err);
		})
}
}
</script>
<style scoped>
.upload{
  margin-left: 20px;
  margin-top: 20px;
  width: 350px;
  height: 600px;
}
.article_list{
  margin-top: 20px;
  width: 950px;
}

.writer_avatar{
    width:50px;
    height:50px;
}
.clearfix{
    text-align: left;
    margin-left: 50px;
    margin-bottom: 20px;
}
.title{
    font-size: 20px;
    font-weight:bold;
    margin-bottom: 10px;
}
.time{
    margin-bottom: 10px;
    font-size: 12px;
    color:#939498;
}
.article_content{
     margin-bottom: 50px;
     font-family: "Hiragino Sans GB","Microsoft YaHei",\5FAE\8F6F\96C5\9ED1,tahoma,arial,simsun,\5B8B\4F53;
     font-size: 14px;
}
.content{
    padding-left:50px;
    padding-right:50px;
}
.box-card{
    /* width: 800px; */
    margin-left: 300px;
}
.writer_name{
    font-size: 14px;
    font-weight:bold;
}
.picture{
  width: 200px;
  height: 200px;
}
.icon-like {
    content: url(../../src/assets/like.png);
    width: 13px;
    padding-right:5px;
}
.icon-command {
    content: url(../../src/assets/command.png);
    width: 13px;
    padding-right:5px;
  }
</style>