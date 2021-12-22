<template>
<div>
  <div>
    <Guidebar></Guidebar>
  </div>
  <div id="recommand-tag">
    <el-row>
      <el-col :span="16">
        <div class="grid-content bg-white">
          <div id="all-tag">
            <p class="reminder">我的订阅</p>
            <div id="tag-table">
              <el-table :data="MyLikeTag" stripe style="width: 100%">
                <el-table-column prop="tagName" label="订阅标签" width="400" align="center">
                  <template slot-scope="scope">
                    <el-button
                      @click.native.prevent="liketag(scope.$index, scope.row)"
                      type="text" class="skiptag-btn">
                      # {{scope.row.tagName}}
                    </el-button>
                  </template>
                </el-table-column>
                <el-table-column prop="articleNum" label="活跃度" width="400" align="center"></el-table-column>
              </el-table>
            </div>
            <p class="reminder">发现更多兴趣</p>
            <div id="tag-table">
              <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="tagName" label="推荐标签" width="400" align="center">
                  <template slot-scope="scope">
                    <el-button
                      @click.native.prevent="tag(scope.$index, scope.row)"
                      type="text" class="skiptag-btn">
                      # {{scope.row.tagName}}
                    </el-button>
                  </template>
                </el-table-column>
                <el-table-column prop="articleNum" label="活跃度" width="400" align="center"></el-table-column>
              </el-table>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-white">
          <div id="pic">
            <img src="../../src/assets/discover_pic1.png" width="350" height="350">
            <img src="../../src/assets/discover_pic2.png" width="300">
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</div>
</template>

<script>
import Guidebar from '@/components/Guidebar'
  export default {
    name: 'Discover',
    components: {
      Guidebar
    },
    mounted:function(){
      this.getLikeTag(),
      this.getTopTag()
    },
    methods: {
      liketag(index, row) {
        console.log('index:',index);
        console.log('tagID:',this.MyLikeTag[index].tagId);
        this.$router.push({path:'/Tag',query:{tagID:this.MyLikeTag[index].tagId}});
      },
      tag(index, row) {
        console.log('index:',index);
        console.log('tagID:',this.tableData[index].tagId);
        this.$router.push({path:'/Tag',query:{tagID:this.tableData[index].tagId}});
      },
      getLikeTag(){
        this.$axios({
          method:"get",
          url: 'api/tag/getMyLikeTag',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('订阅列表1：', res.data);
          this.MyLikeTag=res.data.data.list;
        },err=>{
          console.log(err);
        })
      },
      getTopTag(){
        this.$axios({
          method:"get",
          url: 'api/tag/topTag',
          headers: { token:window.sessionStorage.getItem("token")}
        }).then(res=>{
          console.log('全部列表2：', res.data);
          this.tableData=res.data.data.tagAllList;
        },err=>{
          console.log(err);
        })
      }
    },
    data() {
      return {
        MyLikeTag:[{
          tagName: '',
          articleNum: '',
          tagId:''
        }],
        tableData: [{
          tagName: '',
          articleNum: '',
          tagId:''
        }]
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 30px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-white {
    background: #ffffff;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  #all-tag {
    float: right;
  }
  #tag-table {
    border-top-style: solid;
    border-width: 2px;
    border-color: #939498;
  }
  .skiptag-btn {
    color: #606266;
  }
  .skiptag-btn:hover{
    text-decoration:underline;
  }
  .reminder {
    text-align: center;
    font-size: 20px;
  }
  #pic{
    position: fixed;
    margin-left: 50px;
  }
  #recommand-tag {
    margin-top: 40px;
  }
</style>
