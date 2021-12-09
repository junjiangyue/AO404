<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div id="tag-content">
      <div id="tag-block">
        <div id="tag-name">
          <i class="icon-tag"></i>
          <span id="tag">{{tag_name}}</span>
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
          <span id="article-num">{{article_num}}参与</span>
        </div>
        <hr color=#EFEEEE SIZE=1>
        <div id=join-button>
          <el-button id="join">参与话题</el-button>
        </div>
      </div>
    </div>
    <div id="article-list">
      <el-row>
        <el-col :span="8" v-for="(item) in tabledata" :key="item.id" :offset="0">
          <div class="card">
            <el-card :body-style="{ padding: '0px' }">
              <img src="../../src/assets/discover_pic1.png" class="image">
              <hr color=#EFEEEE SIZE=1>
              <div id="user" style="padding: 0px;">
                <div id="user-name">
                  <p>
                    <img v-bind:src="item.pic" width="50px" align="middle">
                    <span>{{item.tag_name}}</span>
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
  methods: {
    addSubscribe() {
      this.subscribe=1,
      this.$message({
          message: '订阅成功',
          type: 'success'
        });
    },
    cancelSubscribe() {
      this.dialogVisible = false,
      this.subscribe=0,
      this.$message({
          message: '取消成功',
          type: 'success'
        });
    }
  },
  data() {
    return {
      tag_name: "#原神",
      article_num: 1234,
      subscribe: 1,
      dialogVisible: false,
      tabledata: [{
          id: 1,
          tag_name: '原神',
          pic: require('../../src/assets/mlogo.png')
        }, {
          id: 2,
          tag_name: '明日方舟',
          pic: require('../../src/assets/mlogo.png')
        }, {
          id: 3,
          tag_name: '咒术回战',
          pic: require('../../src/assets/mlogo.png')
        }, {
          id: 4,
          tag_name: '鬼灭之刃',
          pic: require('../../src/assets/mlogo.png')
        }, {
          id: 5,
          tag_name: '原神',
          pic: require('../../src/assets/mlogo.png')
        }]
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
</style>
