<template>
  <div class="hello">
    <Guidebar></Guidebar>
      <div class="page_content">
        <el-row>
          <el-col :span="16">
            <div class="article_list" v-for="(item) in tabledata" :key="item.articleId">
            <el-card class="box-card">
                <div  class="clearfix">
                  <el-row>
                    <el-col :span="3">
                      <img style="border-radius: 50%;" class="writer_avatar" :src="item.useravatar"/></el-col>
                      <el-col :span="6" style="padding-top: 10px">
                        <div class="writer_name">{{item.userName}}</div>
                        <div style="font-size:12px;color:#939498">发布了动态</div></el-col>
                  </el-row>
                </div>
                <div class="content">
                    <div class="title" @click="gotoArticleInfo(item.articleId)">{{item.articleHeading}}</div>
                    <div class="time">{{item.publishTime}}</div>
                    <div class="article_content">
                      <el-row>
                        <el-col :span="16">
                      {{item.articleContent}}</el-col>
                        <img v-bind:src="item.picture" width="100%" height="100%"/>
                        </el-row>
                    </div>
                    <el-divider></el-divider>
                    <el-row>
                      <el-col :span="16">
                    <div class="tag" v-for="(tag) in item.tagList" :key="tag.tagId">{{tag.tagName}}</div></el-col>
                    <el-col :span="6">
                        <span style="margin-right:10px;" @click="like" :class="{active: item.isLike}"><i class="icon-like"></i>{{item.articleLikes}}</span>
                        <i class="icon-command" @click="comment(item.articleId)"></i><span>{{item.articleComments}}</span>
                    </el-col>
                    </el-row>
                </div>
              </el-card>
            </div>
          </el-col>
          <el-col :span="4">
            <div >
              <el-card class="upload" shadow="never" style="border:0px;width=100%; height=100%">
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
      useravatar:'',
      tabledata: [
        {
            useravatar:require('../../src/assets/mlogo.png'),
            userName: 'AO404官方小助手',
            articleHeading: '欢迎来到AO404!',
            publishTime:'2021-12-22 0:49',
            articleContent: '这里还什么都没有哦~去发现页看看吧~',
            tagList:'AO404',
            articleLikes:'0',
            articleComments:'0',
            picture:require('../../src/assets/discover_pic1.png'),
            articleId:'',
            isLike:false
          }
      ]
    }
  },
  methods:{
    jumppoatword() {
      this.$router.push('/Postword')
    },
    like(){
      
    },
    comment(articleId){
      this.$router.push({name:'ArticleInfo',params:{articleId:articleId}});
    },
    gotoArticleInfo(articleId){
      console.log(articleId),
      this.$router.push({name:'ArticleInfo',params:{articleId:articleId}});
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
  mounted: function(){
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

    this.$axios({
    method:"get",
    url: 'api/article/homeArticle',
    headers:{
    token:window.sessionStorage.getItem("token")}
    }).then(res=>{
		console.log(res);
    if(res.data.data.homeList.length == 0)
    {
      this.tabledata.articleHeading = '暂时没有关注任何人，去发现页看看吧~'
    }
    else this.tabledata = res.data.data.homeList;
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
    /* border-color: black; */
}
.clearfix{
    text-align: left;
    margin-left: 50px;
    margin-bottom: 20px;
}
.page_content{
  height: 100%;
  width: 100%;
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
    width: 600px;
    margin-left: 250px;
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