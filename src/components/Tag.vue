<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div id="tag-content">
      <div id="tag-block">
        <div id="tag-name">
          <i class="icon-tag"></i>
          <span id="tag">{{tagName}}</span>
          <el-button v-if="subscribe === 0" plain type="danger" size="medium" id="subscribe-btn" @click="addSubscribe">订阅</el-button>
          <el-button v-else plain type="info" size="medium" id="subscribe-btn" @click="dialogVisible = true">已订阅</el-button>
          <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%">
            <span>确认取消订阅该标签？</span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="cancelSubscribe">确 定</el-button>
            </span>
          </el-dialog>
          <span id="article-num">{{articleNum}}参与</span>
        </div>
        <hr color=#EFEEEE SIZE=1>
        <div id=join-button>
          <el-button id="join" @click="joinTag">参与话题</el-button>
        </div>
      </div>
    </div>
    <div id="article-list">
      <el-row>
        <el-col :span="8" v-for="(item) in tabledata" :key="item.id" :offset="0">
          <div class="card">
            <el-card :body-style="{ padding: '0px' }">
              <p class="article_heading">{{item.articleHeading}}</p>
              <div class="content"><p class="article_content">{{item.articleContent}}</p>
              <!--<img src="../../src/assets/discover_pic1.png" class="image">--></div>
              <hr color=#EFEEEE SIZE=1>
              <div id="user" style="padding: 0px;">
                <div id="user-name">
                  <p>
                    <img v-bind:src="item.pic" width="50px" align="middle">
                    <span>
                      <el-button @click="openOtherUserPage(item.userId)" type="text" class="skiptag-btn">
                        {{item.userName}}
                      </el-button>
                    </span>
                  </p>
                </div>
              </div>
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
  name: 'Tag',
  components: {
    Guidebar
  },
  mounted:function(){
    console.log(this.$route.params.tagID);
    this.tagID = this.$route.params.tagID;
    //this.subscribe=this.$route.params.state;
    console.log('tagID:', this.tagID);
    //console.log('subscribe',this.subscribe);
    this.getTagPage();
    this.$axios({
        method:"get",
        url: 'api/user/myInfo',
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('我的信息数据：', res.data);
        this.userId=res.data.data.userId;
        console.log('userId',this.userId);
      },err=>{
        console.log(err);
      });
    this.$axios({
          method:"get",
          url: 'api/tag/getMyLikeTag',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('订阅列表1：', res.data);
          this.MyLikeTag=res.data.data.list;
          var i=0;
          for(i;i<res.data.data.list.length;i++){
            if(this.tagID==this.MyLikeTag[i].tagId){
              this.subscribe=1;
              console.log('是我的订阅');
            }
          }
        },err=>{
          console.log(err);
        })
  },
  methods: {
    addSubscribe() {
      this.$axios({
        method:"post",
        url: 'api/tag/likeTag',
        params:{
            tagId:this.tagID,
        },
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('订阅标签数据：', res.data);
        
      },err=>{
        console.log(err);
      })
      this.subscribe=1,
      this.$message({
          message: '订阅成功',
          type: 'success'
        });
    },
    cancelSubscribe() {
      this.$axios({
        method:"post",
        url: 'api/tag/unlikeTag',
        params:{
            tagId:this.tagID,
        },
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('取消订阅标签数据：', res.data);
        if(res.data.data.msg=="取消订阅成功"){
          this.dialogVisible = false,
          this.subscribe=0,
          this.$message({
              message: '取消成功',
              type: 'success'
          });
        }
      },err=>{
        console.log(err);
      })
    },
    getTagPage() {
      this.$axios({
        method:"get",
        url: 'api/tag/tagPage',
        params:{
          tagId:this.tagID,
        },
        headers: { token:window.sessionStorage.getItem("token")}
      }).then(res=>{
        console.log('文章列表数据：', res.data);
        this.tagName=res.data.data.tagName;
        console.log('tagName',this.tagName);
        this.articleNum=res.data.data.tagInArticle.length;
        console.log('articleNum',this.articleNum);
        this.tabledata=res.data.data.tagInArticle;
      },err=>{
        console.log(err);
      })
    },
    openOtherUserPage(id){
      console.log('打开的userid',id);
      if(id==this.userId){
        console.log('我的id');
        this.$router.push({path:'/PersonalPage'});
      } else {
        console.log('别人的id');
        this.$router.push({name:'OtherUserPage',params:{userID:id}});
      }
    },
    joinTag(){
      console.log('tagName',this.tagName);
      this.$router.push({name:'Postword',params:{tagName:this.tagName}});
    }
  },
  data() {
    return {
      userId:'',
      tagID:'',
      tagName: '',
      articleNum: '',
      subscribe: 0,
      dialogVisible: false,
      tabledata: [{
          articleId: '',
          articleHeading: '',
          articleContent:'',
          pic: require('../../src/assets/mlogo.png'),
          userHead:'',
          userName:'',
          userId:''
        }],
      MyLikeTag:[{
          tagId:''
        }],
    }
  }
}
</script>

<style>
  #tag-block {
    width: 1000px;
    margin: 0px auto;
    border-style: solid;
    border-width: 2px;
    border-color: #DBD8D3;
    border-radius: 20px;
  }
  #tag-name {
    margin-top: 20px;
    margin-left: 30px;
  }
  #tag {
    font-size: 26px;
  }
  .icon-tag {
    content: url(../../src/assets/tag.png);
    width: 22px;
    margin-left: 5px;
    margin-right: 5px;
  }
  #join {
    background-color: #D55245;
    color: rgb(255, 255, 255);
    align-self:flex-start;
    margin-left: 45px;
  }
  #tag-content {
    margin: 40px auto;
  }
  #subscribe-btn {
    margin-left: 20px;
    padding: 8px 20px;
    height: 30px;
    font-size: 12px;
  }
  #article-num {
    float: right;
    margin-top: 10px;
    margin-right: 30px;
    
  }
  #join-button {
    margin-top: 15px;
    margin-bottom: 15px;
    display: flex;
  }
  #article-list {
    margin: 0px auto;
    width: 1050px;
  }
  .card {
    margin: 20px auto;
    margin-left: 25px;
    margin-right: 25px;
  }
  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }


  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }
  #user {
    display: flex;
  }
  #user-name {
    align-self:flex-start;
    margin-left: 20px;
  }
  .article_heading {
    margin-top: 20px;
    margin-left: 20px;
    font-size: 20px;
  }
  .content{
    height: 300px;
  }
  .article_content{
    margin-top: 20px;
    margin-left: 20px;
    font-size: 16px;
  }
  .skiptag-btn {
    color: #606266;
  }
</style>
