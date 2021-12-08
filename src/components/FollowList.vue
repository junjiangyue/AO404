<template>
  <div>
    <div class="hello">
      <Guidebar></Guidebar>
    </div>
    <div id="page-content">
      <el-row>
        <el-col :span="6"><div class="grid-content"><PersonalMenu></PersonalMenu></div></el-col>
        <el-col :span="18">
          <div class="grid-content">
            <div id="follow-list">
              <div id="allfollow">全部关注（{{follow_num}}个）</div>
              <div>
                <div v-for="(item) in tabledata" :key="item.id">
                  <div>
                    <hr color=#EFEEEE SIZE=1>
                    <p id="follow-user">
                      <img v-bind:src="item.picture" width="55px" align="middle">
                      <span id="follow-name">{{item.user_name}}</span>
                      <el-button round id="cancle-follow">取消关注</el-button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
import PersonalMenu from '@/components/PersonalMenu'
export default {
  name: 'FollowList',
  components: {
    Guidebar,
    PersonalMenu
  },
  data() {
    return {
      follow_num:20,
      tabledata: [{
          id: 1,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
        }, {
          id: 2,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
        }, {
          id: 3,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
        }, {
          id: 4,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
        }, {
          id: 5,
          user_name: '哈哈哈',
          picture: require('../../src/assets/mlogo.png'),
        }]
    }
  }
}
</script>

<style>
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
    padding: 10px;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  #page-content {
    width: 1200px;
    margin: 0px auto;
    margin-top: 30px;
  }
  #follow-list {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
  }
  #allfollow {
    font-size: 20px;
    margin-top: 15px;
    margin-left: 25px;
    margin-bottom: 10px;
  }
  #follow-user {
    margin-left: 25px;
  }
  #follow-name {
    font-size: 18px;
    margin-left: 10px;
  }
  #cancle-follow {
    float: right;
    margin-right: 80px;
  }
</style>