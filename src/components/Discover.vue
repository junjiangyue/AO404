<template>
    <el-row>
        <el-col :span="16">
            <div class="grid-content bg-white">
                <div id="tag-table">
                    <el-table :data="tableData" stripe style="width: 100%">
                        <el-table-column prop="tag_name" label="推荐标签" width="400" align="center">

                        </el-table-column>
                        <el-table-column prop="article_num" label="活跃度" width="400" align="center">

                        </el-table-column>
                    </el-table>
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
</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#明日方舟',
          article_num: '156'
        }, {
          tag_name: '#咒术回战',
          article_num: '265'
        }, {
          tag_name: '#鬼灭之刃',
          article_num: '123'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }, {
          tag_name: '#原神',
          article_num: '1234'
        }]
      }
    }
  }
</script>

<style scoped>
  .el-row {
    margin-bottom: 20px;
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
  #tag-table {
      float: right;
  }
  #pic{
      position: fixed;
  }
</style>
