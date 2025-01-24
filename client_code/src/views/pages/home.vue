<template>
	<div>
		<div class="home_box">
			<!-- 历史文化街区推荐 -->
			<div class="recomList_view">
				<div class="recomList_title">
                    <span>历史文化街区推荐</span>
                </div>
				<div class="categoryList">
					<div class="item" @click="lishiwenhuajiequRecomCategoryChange(-1)" style="cursor: pointer" :class="{active:lishiwenhuajiequRecom_index==-1}">全部</div>
					<div class="item" v-for="(item,index) in lishiwenhuajiequRecomCategorys" @click="lishiwenhuajiequRecomCategoryChange(index)" :class="{active:lishiwenhuajiequRecom_index==index}" style="cursor: pointer">
						{{item}}
					</div>
				</div>
				<div class="recommendList">
					<div class="item" v-for="(item,index) in lishiwenhuajiequRecomList" :key="index" @click="detailClick('lishiwenhuajiequ',item.id)">
						<div class="img-box">
							<img v-if="isHttp(item.jingdiantupian)" :src="item.jingdiantupian.split(',')[0]" alt="">
							<img v-else :src="item.jingdiantupian?$config.url + item.jingdiantupian.split(',')[0]:''" alt="">
						</div>
						<div class="content-box">
							<div class="title">
								{{item.jiequmingcheng}}
							</div>
							<div class="title">
								开放时间：{{item.kaifangshijian}}
							</div>
							<div class="title">
								地址：{{item.dizhi}}
							</div>
							<div class="statistic">
								<div class="like">
									<span class="iconfont icon-thumb-up-line1"></span>
									<div class="num">{{item.thumbsupNumber}}</div>
								</div>
								<div class="collect">
									<span class="iconfont icon-likeline4"></span>
									<div class="num">{{item.storeupNumber}}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="recommend_more_view" @click="moreClick('lishiwenhuajiequ')">
					<span class="recommend_more_text">更多 +</span>
				</div>
			</div>
			<!-- 公告信息首页展示 -->
			<div class="homeList_view">
				<div class="homeList_title">
                    <span>公告信息展示</span>
                </div>
				<div class="homeList">
					<div class="item" v-for="(item,index) in gonggaoxinxiHomeList" :key="index" @click="detailClick('gonggaoxinxi',item.id)">
						<div class="img-box">
							<img v-if="isHttp(item.fengmian)" :src="item.fengmian.split(',')[0]" alt="">
							<img v-else :src="item.fengmian?$config.url + item.fengmian.split(',')[0]:''" alt="">
						</div>
						<div class="content-box">
							<div class="title">
								{{item.biaoti}}
							</div>
							<div class="title">
								{{item.faburiqi}}
							</div>
						</div>
					</div>
				</div>
				<div class="homeList_more_view" @click="moreClick('gonggaoxinxi')">
					<span class="homeList_more_text">更多 +</span>
				</div>
			</div>
			<div class="appendBox1"></div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		getCurrentInstance
	} from 'vue';
	import moment from 'moment'
	import {
		useRouter
	} from 'vue-router';
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	const router = useRouter()
	//历史文化街区推荐
	const lishiwenhuajiequRecomCategorys = ref([])
	const lishiwenhuajiequRecom_index = ref(-1)
	const getlishiwenhuajiequRecomCategorys = () => {
		context?.$http({
			url: 'option/jingdianleixing/jingdianleixing',
			method: 'get'
		}).then(res => {
			lishiwenhuajiequRecomCategorys.value = res.data.data
		})
	}
	getlishiwenhuajiequRecomCategorys()
	const lishiwenhuajiequRecomCategoryChange = (index)=>{
		lishiwenhuajiequRecom_index.value = index
		getlishiwenhuajiequRecomList()
	}
	const lishiwenhuajiequRecomList = ref([])
	const getlishiwenhuajiequRecomList = () => {
		let autoSortUrl = 'lishiwenhuajiequ/autoSort'
		if(context?.$toolUtil.storageGet('frontToken')){
			autoSortUrl = "lishiwenhuajiequ/autoSort2"
		}
		let params = {
			sfsh: '是',
			page: 1,
			limit: 4
		}
		if(lishiwenhuajiequRecom_index.value>=0){
            params.jingdianleixing = lishiwenhuajiequRecomCategorys.value[lishiwenhuajiequRecom_index.value]
		}
		context?.$http({
			url: autoSortUrl,
			method: 'get',
			params: params
		}).then(res => {
			lishiwenhuajiequRecomList.value = res.data.data.list
			lishiwenhuajiequRecomList.value.forEach(item=>{
				if(!isHttp(item.jingdiantupian)){
					item.imgUrls = item.jingdiantupian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//公告信息首页展示
	const gonggaoxinxiHomeList = ref([])
	const getgonggaoxinxiHomeList = () => {
		let params = {
			page: 1,
			limit: 8
		}
		context?.$http({
			url: 'gonggaoxinxi/list',
			method: 'get',
			params: params
		}).then(res => {
			gonggaoxinxiHomeList.value = res.data.data.list
			gonggaoxinxiHomeList.value.forEach(item=>{
				if(!isHttp(item.fengmian)){
					item.imgUrls = item.fengmian.split(',').map(item=>context.$config.url+item)
				}
			})
		})
	}
	//判断图片链接是否带http
	const isHttp = (str) => {
        return str && str.substr(0,4)=='http';
    }
	//跳转详情
	const detailClick = (table,id) => {
		router.push(`/index/${table}Detail?id=${id}`)
	}
	const moreClick = (table) => {
		router.push(`/index/${table}List`)
	}
	const init = () => {
		//历史文化街区推荐
		getlishiwenhuajiequRecomList()
		//公告信息首页展示
		getgonggaoxinxiHomeList()
	}
	init()
</script>

<style lang="scss">
	.home_box {
	}

	// 推荐
	.recomList_view {
		.recomList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1.05) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.recommend_more_view {
			cursor: pointer;
			top: 20px;
			position: absolute;
			right: 20px;
			.recommend_more_text {
			}
		}
	}
	// 推荐
	// 新闻资讯
	.newsList_view {

		.newsList_title {
		}
		// list
		.news_list_one {
			display: flex;
			flex-wrap: wrap;
			.news_item {
				box-shadow: 0 4px 6px rgba(0,0,0,.3);
				margin: 0 10px 10px;
				background: #fff;
				display: flex;
				width: calc(33% - 20px);
				align-items: center;
				.news_img_box {
					width: 40%;
					font-size: 0;
					.news_img {
						object-fit: cover;
						width: 100%;
						height: 200px;
					}
				}
				.news_content {
					margin: 0 0 0 20px;
					width: calc(60% - 20px);
					.news_title {
						font-weight: bold;
						font-size: 20px;
					}
					.news_text {
						font-size: 14px;
						line-height: 1.5;
					}
					.news_time {
						color: #999;
						width: 100%;
						text-align: right;
					}
				}
			}
		}
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.news_more_view {
			.news_more_text {
			}
			.el-icon {
			}
		}
	}
	// 新闻资讯
	// 首页展示
	.homeList_view {

		.homeList_title {
		}
		// list
		// list
		// animation
		.animation_box {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		.animation_box img {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			z-index: initial;
		}
		.animation_box img:hover {
			transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			-webkit-perspective: 1000px;
			perspective: 1000px;
			transition: 0.3s;
		}
		// animation
		// 更多
		.homeList_more_view {
			cursor: pointer;
			.homeList_more_text {
			}
		}
	}
	// 首页展示
</style>
<style>
.home_box{
    width: 1220px;
    margin: 0px auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}
.home_box .appendBox1{
    width: 100%;
    height:0px;
    background:#f00;
    order:5;
}
/* 总盒子 */
.recomList_view {
    width: 100%;
    margin:20px 10px 0;
    padding: 15px 20px;
    background:linear-gradient(180deg, rgba(255,255,255,1) 0%, var(--theme2-color10) 100%),#fff;
    border-width: 1px;
    border-style: solid;
    border-color:var(--theme2-color50);
    position:relative;
    order: 4;
    display: block;
}
/* 标题 */
.recomList_view .recomList_title{
    width: 101%;
    border-width: 0 0 1px;
    border-style: solid;
    border-color: var(--theme2-color);
    margin: 0 0 20px;
}
.recomList_view .recomList_title span{
    display:inline-block;
    font-size: 16px;
    color:#333;
    font-weight:600;
    padding-bottom:10px;
    border-bottom:2px solid var(--theme2-color);
}

/* 分类 */
.recomList_view .categoryList{
    display: none;
    flex-wrap: wrap;
}
.recomList_view .categoryList .item{
}

/* 样式七 总盒子 */
.recomList_view .recommendList{
    display: flex;
    flex-wrap: wrap;
    margin: 0px auto;
}
.recomList_view .recommendList .item{
    width: calc(25% - 20px);
    margin: 0px 10px 20px;
    background: none;
    cursor: pointer;
}
.recomList_view .recommendList .item .img-box{
    width: 100%;
    height: 180px;
    overflow: hidden;
    margin: 0px 0px 5px;
}
.recomList_view .recommendList .item .img-box img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.recomList_view .recommendList .item .content-box{
    width: 100%;
    background: none;
}
.recomList_view .recommendList .item .content-box .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.recomList_view .recommendList .item .content-box .price{
    width: 100%;
    text-align:right;
    color: #f00;
    font-size:16px;
}
.recomList_view .recommendList .item .content-box .statistic{
    width: 100%;
    display: none;
    align-items: center;
    margin:10px 0 0;
}
.recomList_view .recommendList .item .content-box .statistic .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
.recomList_view .recommendList .item .content-box .statistic .num{
    color: inherit;
}
.recomList_view .recommendList .item .content-box .statistic .like{
    display: flex;
    align-items: center;
    color: rgb(153, 153, 153);
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.recomList_view .recommendList .item .content-box .statistic .collect{
    display: flex;
    align-items: center;
    color: rgb(153, 153, 153);
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.recomList_view .recommendList .item .content-box .statistic .clickNum{
    display: flex;
    align-items: center;
    color: rgb(153, 153, 153);
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
/* 总盒子 */
.homeList_view{
    width: 1200px;
    margin: 20px 10px 0;
    padding: 15px 20px;
    background:linear-gradient(180deg, rgba(255,255,255,1) 0%, var(--theme2-color10) 100%),#fff;
    border-width: 1px;
    border-style: solid;
    border-color: var(--theme2-color50);
    position:relative;
    order: 6;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
    align-items: flex-start;
    align-content: flex-start;
}
/* 标题 */
.homeList_view .homeList_title{
    width: 101%;
    border-width: 0 0 1px;
    border-style: solid;
    border-color: var(--theme2-color);
    margin: 0 0 20px;
}
.homeList_view .homeList_title span{
    display:inline-block;
    font-size: 16px;
    color:#333;
    font-weight:600;
    padding-bottom:10px;
    border-bottom:2px solid var(--theme2-color);
}

/* 分类 */
.homeList_view .categoryList{
    width: 200px;
    display: block;
    flex-wrap: wrap;
    margin-right:40px;
    order:1;
    border:1px solid var(--theme-color50);
}
.homeList_view .categoryList .item{
    background: #fff;
    line-height:50px;
    color:#333;
    text-align: center;
    margin:0 0 0px;
    cursor:pointer;
    font-size: 15px;
    border-bottom:1px solid var(--theme-color50);
}
.homeList_view .categoryList .item:last-child{
    border-bottom:none;
}
.homeList_view .categoryList .item:hover{
    background:var(--theme-color);
    color:#fff;
}
.homeList_view .categoryList .item.active{
    background:var(--theme-color);
    color:#fff;
}

/* 样式七 总盒子 */
.homeList_view .homeList{
    width: calc(100% - 240px);
    margin: 0;
    display: flex;
    flex-wrap: wrap;
    justify-content:flex-start;
    order:2;
    flex:1;
}
.homeList_view .homeList .item{
    width: calc(25% - 20px);
    margin: 0px 0px 20px 20px;
    background: none;
    cursor: pointer;
    overflow:hidden;
    position:relative;
}
.homeList_view .homeList .item .img-box{
    width: 100%;
    height: 168px;
    overflow: hidden;
    margin: 0px;
    text-align:center;
}
.homeList_view .homeList .item .img-box img{
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.homeList_view .homeList .item .content-box{
    width: 100%;
    position:absolute; left:0px; bottom:-168px;  height:168px; background:rgba(255, 255, 255, .8);  padding:0px; transition:all 0.5s; display:flex; flex-wrap:wrap; align-items:center; justify-content:center;flex-direction:column; text-align:center;
}
.homeList_view .homeList .item:hover .content-box{ bottom:0px; background:rgba(0, 0, 0, .5); height:168px; }

.homeList_view .homeList .item .content-box .title{
    width: 100%;
    line-height: 24px;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
}
.homeList_view .homeList .item:hover .content-box .title{
    color: #eee;
}
.homeList_view .homeList .item .content-box .price{
    width: 100%;
    text-align:center;
    color: #f00;
    font-size:16px;
}
.homeList_view .homeList .item:hover .content-box .price{
}
.homeList_view .homeList .item .content-box .statistic{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin:10px 0 0;
}
.homeList_view .homeList .item:hover .content-box .statistic{
    color: #ccc;
}
.homeList_view .homeList .item .content-box .statistic .iconfont{
    color: inherit;
    margin: 0px 4px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .num{
    color: inherit;
}
.homeList_view .homeList .item .content-box .statistic .like{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .collect{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}
.homeList_view .homeList .item .content-box .statistic .clickNum{
    display: flex;
    align-items: center;
    color: inherit;
    font-size: 16px;
    margin: 0px 10px 0px 0px;
}

/* 查看更多 */
.homeList_view .homeList_more_view{
    width: auto;
    text-align: center;
    background: none;
    margin: 0;
    cursor: pointer;
    display: inline-block;
    font-size: 15px;
    color: rgb(51, 51, 51);
    padding: 0px;
    position:absolute;
    right:20px;
    top: 15px;
}
.homeList_view .homeList_more_view .news_more_text{
}
.newsList_view .news_more_view .el-icon-d-arrow-right{
}


</style>