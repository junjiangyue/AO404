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
            <div id="user-info">
              <div id="info">账号资料</div>
              <hr color=#EFEEEE SIZE=1>
              <div id="photoblock"><p><img v-bind:src="pic" id="headphoto"></p></div>
              <div id="userinfo">
                <p>账&ensp;号&ensp;ID：{{user_id}}</p>
                <p>昵&emsp;&emsp;称：{{user_name}}</p>
                <p>关联邮箱：{{email}}</p>
                <el-button id="changeinfo" @click="infoDialogVisible = true">修改资料</el-button>
                <el-dialog title="修改资料" :visible.sync="infoDialogVisible" width="40%" center>
                  <div id="input-info">
                    <p>昵&emsp;&emsp;称：<el-input  placeholder="请输入内容"  v-model="input_name"  clearable style="width: 300px"></el-input></p>
                    <p>关联邮箱：<el-input  placeholder="请输入内容"  v-model="input_email"  clearable style="width: 300px"></el-input></p>
                  </div>
                  <span slot="footer" class="dialog-footer">
                    <el-button @click="infoDialogVisible = false">取消修改</el-button>
                    <el-button type="primary" @click="confirmDialogVisible = true">提交修改</el-button>
                    <el-dialog title="提示" :visible.sync="confirmDialogVisible" width="25%" center append-to-body>
                      <span>确认提交修改？</span>
                      <span slot="footer" class="dialog-footer">
                        <el-button @click="confirmDialogVisible = false">取 消</el-button>
                        <el-button type="primary" @click="confirmDialogVisible = false">确 定</el-button>
                      </span>
                    </el-dialog>
                  </span>
                </el-dialog>
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
  name: 'UserInfo',
  components: {
    Guidebar,
    PersonalMenu
  },
  data() {
    return {
      user_id: 12345,
      user_name: '原神',
      pic: require('../../src/assets/mlogo.png'),
      email: '123456@qq.com',
      input_name: '哈哈哈',
      input_email:'123456@qq.com',
      infoDialogVisible: false,
      confirmDialogVisible: false
    }
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
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
  #user-info {
    border-style: solid;
    border-width: 2px;
    border-color: #e5e9f2;
    border-radius: 4px;
  }
  #info {
    font-size: 20px;
    margin-top: 15px;
    margin-left: 25px;
    margin-bottom: 10px;
  }
  #photoblock{
    text-align:center;
  }
  #headphoto {
    width: 120px;
  }
  #userinfo {
    margin-left: 300px;
    font-size: 18px;
    margin-bottom: 40px;
  }
  #changeinfo {
    margin-left: 100px;
    margin-top: 20px;
  }
  #input-info {
    text-align:center;
    font-size: 16px;
  }
</style>