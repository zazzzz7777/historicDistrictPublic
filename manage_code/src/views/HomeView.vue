<template>
  <div class="home_view">
    <div class="projectTitle">欢迎使用 {{ projectName }}</div>
    <div class="count_list">
      <el-collapse-transition v-if="btnAuth('yonghu', '首页总数')">
        <el-card
          v-show="countTypeList.closeyonghuCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">用户</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenyonghuCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenyonghuCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeyonghuCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenyonghuCountType"
            >
              <div class="count_title">用户总数</div>
              <div class="count_num">{{ yonghuCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('goupiaoxinxi', '首页总数')">
        <el-card
          v-show="countTypeList.closegoupiaoxinxiCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">购票信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddengoupiaoxinxiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddengoupiaoxinxiCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closegoupiaoxinxiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddengoupiaoxinxiCountType"
            >
              <div class="count_title">购票信息总数</div>
              <div class="count_num">{{ goupiaoxinxiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('yuyuexinxi', '首页总数')">
        <el-card
          v-show="countTypeList.closeyuyuexinxiCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">预约信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenyuyuexinxiCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenyuyuexinxiCountType ? 'showIcons1' : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closeyuyuexinxiCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenyuyuexinxiCountType"
            >
              <div class="count_title">预约信息总数</div>
              <div class="count_num">{{ yuyuexinxiCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
      <el-collapse-transition v-if="btnAuth('lishiwenhuajiequ', '首页总数')">
        <el-card
          v-show="countTypeList.closelishiwenhuajiequCountType"
          class="card_view count-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">历史文化街区</div>
              <div class="card_head_right">
                <el-icon
                  @click="countTypeClick('hiddenlishiwenhuajiequCountType')"
                  class="showIcons"
                  :class="
                    countTypeList.hiddenlishiwenhuajiequCountType
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="countTypeClick('closelishiwenhuajiequCountType')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="count_item"
              v-show="countTypeList.hiddenlishiwenhuajiequCountType"
            >
              <div class="count_title">历史文化街区总数</div>
              <div class="count_num">{{ lishiwenhuajiequCount }}</div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
    <div class="card_list">
      <el-collapse-transition v-if="btnAuth('goupiaoxinxi', '首页统计')">
        <el-card
          v-show="cardTypeList.closegoupiaoxinxiChartType1"
          class="card_view chart-item"
        >
          <template #header>
            <div class="index_card_head">
              <div class="card_head_title">购票信息</div>
              <div class="card_head_right">
                <el-icon
                  @click="cardTypeClick('hiddengoupiaoxinxiChartType1')"
                  class="showIcons"
                  :class="
                    cardTypeList.hiddengoupiaoxinxiChartType1
                      ? 'showIcons1'
                      : ''
                  "
                >
                  <ArrowUpBold />
                </el-icon>
                <el-icon
                  @click="cardTypeClick('closegoupiaoxinxiChartType1')"
                  class="closeIcons"
                >
                  <CloseBold />
                </el-icon>
              </div>
            </div>
          </template>
          <el-collapse-transition>
            <div
              class="card_item"
              v-show="cardTypeList.hiddengoupiaoxinxiChartType1"
            >
              <div
                id="goupiaoxinxijiequmingchengEchart1"
                class="Echart"
                style="width: 100%; height: 400px"
              ></div>
            </div>
          </el-collapse-transition>
        </el-card>
      </el-collapse-transition>
    </div>
  </div>
</template>

<script setup>
import { inject, nextTick, ref, getCurrentInstance } from 'vue'
const context = getCurrentInstance()?.appContext.config.globalProperties
const projectName = context.$project.projectName
const countTypeList = ref({})
const getCountList = () => {
  countTypeList.value.closeyonghuCountType = true
  countTypeList.value.hiddenyonghuCountType = true
  if (btnAuth('yonghu', '首页总数')) {
    getyonghuCount()
  }
  countTypeList.value.closegoupiaoxinxiCountType = true
  countTypeList.value.hiddengoupiaoxinxiCountType = true
  if (btnAuth('goupiaoxinxi', '首页总数')) {
    getgoupiaoxinxiCount()
  }
  countTypeList.value.closeyuyuexinxiCountType = true
  countTypeList.value.hiddenyuyuexinxiCountType = true
  if (btnAuth('yuyuexinxi', '首页总数')) {
    getyuyuexinxiCount()
  }
  countTypeList.value.closelishiwenhuajiequCountType = true
  countTypeList.value.hiddenlishiwenhuajiequCountType = true
  if (btnAuth('lishiwenhuajiequ', '首页总数')) {
    getlishiwenhuajiequCount()
  }
}
const yonghuCount = ref(0)
const getyonghuCount = () => {
  context
    ?.$http({
      url: 'yonghu/count',
      method: 'get',
    })
    .then((res) => {
      yonghuCount.value = res.data.data
    })
}
const goupiaoxinxiCount = ref(0)
const getgoupiaoxinxiCount = () => {
  context
    ?.$http({
      url: 'goupiaoxinxi/count',
      method: 'get',
    })
    .then((res) => {
      goupiaoxinxiCount.value = res.data.data
    })
}
const yuyuexinxiCount = ref(0)
const getyuyuexinxiCount = () => {
  context
    ?.$http({
      url: 'yuyuexinxi/count',
      method: 'get',
    })
    .then((res) => {
      yuyuexinxiCount.value = res.data.data
    })
}
const lishiwenhuajiequCount = ref(0)
const getlishiwenhuajiequCount = () => {
  context
    ?.$http({
      url: 'lishiwenhuajiequ/count',
      method: 'get',
    })
    .then((res) => {
      lishiwenhuajiequCount.value = res.data.data
    })
}
const countTypeClick = (e) => {
  countTypeList.value[e] = !countTypeList.value[e]
}
//权限验证
const btnAuth = (e, a) => {
  return context?.$toolUtil.isAuth(e, a)
}
let echarts = inject('echarts')
const cardTypeClick = (e) => {
  cardTypeList.value[e] = !cardTypeList.value[e]
  setTimeout(() => {
    getCardList()
  }, 1000)
}
const cardTypeList = ref({
  closegoupiaoxinxiChartType1: true,
  hiddengoupiaoxinxiChartType1: true,
})
const getCardList = () => {
  if (btnAuth('goupiaoxinxi', '首页统计')) {
    getgoupiaoxinxiChart1()
  }
}
import '@/assets/js/echarts-theme'
const getgoupiaoxinxiChart1 = () => {
  nextTick(() => {
    var jiequmingchengEchart1 = echarts.init(
      document.getElementById('goupiaoxinxijiequmingchengEchart1'),
      'theme'
    )
    context
      ?.$http({
        url: 'goupiaoxinxi/group/jiequmingcheng',
        method: 'get',
      })
      .then((obj) => {
        let res = obj.data.data
        let xAxis = []
        let yAxis = []
        let dataList = []
        for (let i = 0; i < res.length; i++) {
          xAxis.push(res[i].jiequmingcheng)
          yAxis.push(parseFloat(res[i].total))
          dataList.push({
            value: parseFloat(res[i].total),
            name: res[i].jiequmingcheng,
          })
        }
        var option = {}
        option = {
          title: {
            text: '景点购票数量统计',
            left: 'center',
          },
          tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}',
          },
          xAxis: {
            data: xAxis,
            type: 'category',
          },
          yAxis: {
            type: 'value',
          },
          series: {
            data: yAxis,
            type: 'bar',
          },
        }

        jiequmingchengEchart1.clear()
        // 使用刚指定的配置项和数据显示图表。
        jiequmingchengEchart1.setOption(option)
        //根据窗口的大小变动图表
        jiequmingchengEchart1.resize()
      })
  })
}

const init = () => {
  getCountList()
  getCardList()
}
init()
</script>
<style lang="scss">
// 首页盒子
.home_view {
  background-image: url('http://localhost:8080/historicDistrict/file/2ba09c686491481982bda72452d5ca8f.webp');
}
.home_view .projectTitle {
}

.showIcons {
  transition: transform 0.3s;
  margin-right: 10px;
}

.showIcons1 {
  transform: rotate(-180deg);
}

// 总数盒子
.count_list {
  // 总数card
  .card_view {
    // card头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .count_item {
        // 总数标题
        .count_title {
        }
        // 总数数字
        .count_num {
        }
      }
    }
  }
}

// 统计图盒子
.card_list {
  // 统计图card
  .card_view {
    // 头部
    .el-card__header {
      // 头部盒子
      .index_card_head {
        // 标题
        .card_head_title {
        }
        // 按钮盒子
        .card_head_right {
          // 按钮
          .el-icon {
          }
        }
      }
    }
    // body
    .el-card__body {
      // body盒子
      .card_item {
      }
    }
  }
}
</style>
<style>
/*总盒子*/
.home_view {
  padding: 20px 30px 20px 40px;
  margin: 0px;
  height: auto;
  min-height: 100vh;
  background: url() no-repeat center top / cover !important;
  width: 100%;
  border-radius: 0px;
  display: flex;
  flex-wrap: wrap;
  align-items: flex-start;
}
.home_view .projectTitle {
  width: 100%;
  font-size: 26px;
  font-weight: bold;
  padding: 40px 0px 20px;
  height: auto;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0px 0px 20px;
  color: rgb(55, 55, 55);
}

/*总数*/
/*总盒子*/
.home_view .count_list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 0px 0px 20px;
  width: 100%;
  align-items: flex-start;
}
/*卡片盒子*/
.home_view .count_list .card_view {
  width: 23%;
  height: auto;
  margin: 0px 1% 20px;
  box-sizing: border-box;
  border: 0px;
  border-radius: 0px;
  flex: 1;
  padding: 0 1%;
  box-shadow: none;
}

.home_view .count_list .card_view:nth-child(1) {
}
.home_view .count_list .card_view:nth-child(2) {
}
.home_view .count_list .card_view:nth-child(3) {
}
.home_view .count_list .card_view:nth-child(4) {
}
.home_view .count_list .card_view:nth-child(2n + 5) {
}
.home_view .count_list .card_view:nth-child(2n + 6) {
}
.home_view .count_list .card_view:nth-child(1):hover {
}
.home_view .count_list .card_view:nth-child(2):hover {
}
.home_view .count_list .card_view:nth-child(3):hover {
}
.home_view .count_list .card_view:nth-child(4):hover {
}
.home_view .count_list .card_view:nth-child(2n + 5):hover {
}
.home_view .count_list .card_view:nth-child(2n + 6):hover {
}

/*head 总盒子*/
.home_view .count_list .card_view .el-card__header {
  width: 100%;
  border: 0px solid rgb(238, 238, 238);
  display: none;
}
/*item*/
.home_view .count_list .card_view .el-card__header .index_card_head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 10px;
}
/*标题*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_title {
  font-size: 14px;
  color: rgb(51, 51, 51);
}
/*按钮盒子*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right {
  display: flex;
  align-items: center;
}
/*按钮*/
.home_view
  .count_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right
  .showIcons {
  color: rgb(102, 102, 102);
  font-size: 20px;
  cursor: pointer;
}
/*body 总盒子*/
.home_view .count_list .card_view .el-card__body {
  padding: 0px;
  background: none;
}
/*item*/
.home_view .count_list .card_view .el-card__body .count_item {
  padding: 10px;
  text-align: left;
  display: flex;
  align-items: flex-start;
  justify-content: flex-start;
  flex-direction: column;
}
/*标题*/
.home_view .count_list .card_view .el-card__body .count_item .count_title {
  font-size: 18px;
  color: rgb(0, 0, 0);
  line-height: 2;
  display: inline-block;
  margin: 0px 10px 0px 0px;
  font-weight: 500;
  width: 100%;
  text-align: center;
}
/*数字*/
.home_view .count_list .card_view .el-card__body .count_item .count_num {
  width: 100%;
  font-size: 30px;
  color: #fac12c;
  line-height: 2;
  display: inline-block;
  font-weight: 500;
  text-align: center;
}

/*图表*/
/*总盒子*/
.home_view .card_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  padding: 0px 0px 20px;
  align-items: flex-start;
  order: 11;
}
/*卡片 总盒子*/
.home_view .card_list .card_view {
  width: 48%;
  height: auto;
  margin: 0px 1% 30px;
  box-sizing: border-box;
  border: 0px solid #ddd;
  border-radius: 0px;
  padding: 20px 0px 0px;
  box-shadow: 0 0px 0px 0 rgba(0, 0, 0, 0.1);
}
/*head 总盒子*/
.home_view .card_list .card_view .el-card__header {
  width: 100%;
  border: 0px solid rgb(238, 238, 238);
  background: rgb(255, 255, 255);
  display: none;
}
/*item*/
.home_view .card_list .card_view .el-card__header .index_card_head {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 10px;
}
/*标题*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_title {
  color: rgb(33, 33, 33);
}
/*按钮盒子*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right {
  display: none;
}
/*按钮*/
.home_view
  .card_list
  .card_view
  .el-card__header
  .index_card_head
  .card_head_right
  .showIcons {
  color: rgb(238, 238, 238);
  font-size: 20px;
  cursor: pointer;
}

/*body 总盒子*/
.home_view .card_list .card_view .el-card__body {
  padding: 0px;
  background: rgb(255, 255, 255);
}
.home_view .card_list .card_view .el-card__body .card_item {
  padding: 10px;
  text-align: center;
}

.home-calendar {
  border: 0px solid rgb(218, 218, 218);
  box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 0px;
  margin: 0px auto 30px;
  border-radius: 0px;
  color: rgb(102, 102, 102);
  background: #fff;
  width: calc(100% - 30px);
  font-size: inherit;
  height: auto;
  padding: 0 0 10px;
  order: 2;
}
.home-calendar .header {
  padding: 17px 40px;
  border-color: rgba(126, 96, 16, 0.1);
  flex-wrap: wrap;
  background: #fff;
  border-width: 0px 0px 1px;
  display: flex;
  width: 100%;
  border-style: solid;
  justify-content: space-between;
  height: auto;
}
.home-calendar .header .btn {
  cursor: pointer;
  border: 0px solid rgb(153, 153, 153);
  padding: 0px 0px 0px 12px;
  align-items: center;
  color: #333;
  border-radius: 4px;
  display: flex;
  width: auto;
  font-size: 16px;
  justify-content: center;
}
.home-calendar .header .title {
  padding: 0px 10px;
  font-size: 24px;
  align-items: center;
  justify-content: center;
  display: flex;
  color: #333;
}
.home-calendar table {
  width: 100%;
  padding: 0px 0px 20px;
  height: auto;
}
.home-calendar tbody,
thead {
  width: 100%;
}
.home-calendar tr {
  width: 100%;
  align-items: center;
  justify-content: center;
  display: flex;
}
.home-calendar th {
  align-items: center;
  flex: 1;
  display: flex;
  line-height: 50px;
  justify-content: center;
}
.home-calendar td {
  cursor: pointer;
  padding: 6px 12px 6px;
  flex: 1;
  display: flex;
  justify-content: center;
  text-align: center;
}
.home-calendar td.today .text {
  width: 80%;
  height: 80%;
  padding: 5px 0;
  background: #2f3f5680;
  color: #fff;
  border-radius: 0px;
}
.home-calendar td.festival .text {
  width: 80%;
  height: 80%;
  padding: 5px 0;
  background: #2f3f5610;
  border-radius: 0px;
}
</style>
