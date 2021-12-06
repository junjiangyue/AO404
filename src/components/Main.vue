<template>
  <div class="hello">
    <Guidebar></Guidebar>
    <div class="page_content">
      <div class="article_list">
        <el-card class="box-card">
             <div  class="clearfix">
               <el-row>
                 <el-col :span="3">
                  <img class="writer_avatar" src="@/assets/mlogo.png"/></el-col>
                  <el-col :span="6" style="padding-top: 10px">
                    <div class="writer_name">作者名称</div>
                    <div style="font-size:12px;color:#939498">发布了动态</div></el-col>
               </el-row>
            </div>
            <div class="content">
                <div class="title">标题</div>
                <div class="time">发布时间</div>
                <div class="article_content">
                  <el-row>
                    <el-col :span="16">
                    <p>【我用早餐叫醒你】</p>
                    <p>弱者才会一蹶不振，我要逆风翻盘。</p>
                    <p>今日份元气早餐：蛋花酒酿/XO酱配法棍🥖/糖心苹果</p></el-col>
                    <el-col :span="4" style="padding-top: 10px">
                    <img class="picture" src="@/assets/discover_pic1.png" width="100%" height="100%"/></el-col>
                    </el-row>
                 </div>
                <div class="tag">#标签</div>
            </div>
          </el-card>
      </div>
      <div >
        <el-card class="upload" shadow="never" style="border:0px">
           <img  src="@/assets/arabica-1300.png" width="100%" height="100%" />
           <div style="text-align:center;font-size:14px;">在这里发布你的任何想法！</div>
           <el-row style="text-align:center;padding-left:35px;padding-top:20px">
             <el-col span="10">
                <img  src="@/assets/posttext.png" width="50px" height="50px" />
                <p style="margin:5px">文字</p>
             </el-col>
             <el-col span="10">
                <img  src="@/assets/postimg.png" width="50px" height="50px" />
                <p style="margin:5px">图片</p>
             </el-col>
           </el-row>
        </el-card>
        </div>
    </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
export default {
  name: 'Main',
  components: {
    Guidebar
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
  float:left;
  margin-top: 20px;
}
.upload{
  float:left;
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
</style>