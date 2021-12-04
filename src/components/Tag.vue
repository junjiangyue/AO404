<template>
    <el-row>
        <el-col :span="24">
            <div class="grid-content bg-purple-dark">
                <div class="tag-name">
                    <img src="../../src/assets/tag.png" width="30px" id="tag-pic">
                    <span id="tag">{{tag_name}}</span>
                    <span id="article-num">{{article_num}}参与</span>
                </div>
                <el-divider></el-divider>
                <el-button id="join">参与话题</el-button>
            </div>
        </el-col>
    </el-row>
</template>

<script>
    export default {
        data() {
            return {
                tag_name: "原神",
                article_num: 1234
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
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  #tag-name {
      vertical-align:middle;
  }
  #tag {
      font-size: 26px;
  }
  #tag-pic {
      padding-top: 10px;
  }
  #join {
      background-color: #D55245;
      color: rgb(255, 255, 255);
  }
</style>
