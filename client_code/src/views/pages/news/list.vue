<template>
	<div class="list-page news-page" :style='{}'>
        <div class="breadcrumb-wrapper" style="width: 100%;">
            <div class="bread_view">
                <el-breadcrumb separator="Ξ" class="breadcrumb">
                    <el-breadcrumb-item class="first_breadcrumb" :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item class="second_breadcrumb" v-for="(item,index) in breadList" :key="index">{{item.name}}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
        </div>
		<el-form inline :model="searchQuery" class="list_search">
			<div class="search_view">
				<div class="search_label">
					标题：
				</div>
				<div class="search_box">
					<el-input v-model="searchQuery.title" placeholder="标题" clearable></el-input>
				</div>
			</div>
			<div class="search_btn_view">
				<el-button class="search_btn" type="primary" @click="searchClick">搜索</el-button>
			</div>
		</el-form>
		<div class="news_list_one">
			<div class="list-item animation_box" v-for="(item,index) in list" :key="index" @click="newsDetail(item.id)">
				<div class="news_img_box">
					<img class="news_img" :src="$config.url + item.picture" alt="">
				</div>
				<div class="news_content">
					<div class="news_title"><span>{{item.title}}</span></div>
					<div class="news_intro">{{item.introduction}}</div>
					<div class="news_time">{{item.addtime.split(' ')[0]}}</div>
				</div>
			</div>
		</div>
		<el-pagination
			background 
			:layout="layouts.join(',')"
			:total="total" 
			:page-size="listQuery.limit"
            v-model:current-page="listQuery.page"
			prev-text="上一页"
			next-text="下一页"
			:hide-on-single-page="false"
			:style='{}'
			@size-change="sizeChange"
			@current-change="currentChange"/>
		
		
		<formModel ref="formModelRef"></formModel>
	</div>
</template>

<script setup>
	import moment from 'moment'
	import formModel from './formModel'
	import {
		ref,
		nextTick,
		getCurrentInstance
	} from 'vue';
	import {
		ElMessageBox
	} from 'element-plus'
	import {
		useRoute,
		useRouter
	} from 'vue-router'
	const context = getCurrentInstance()?.appContext.config.globalProperties;
	//基础信息
	const tableName = 'news'
	const formName = '公告信息'
	const router = useRouter()
	const route = useRoute()
	//基础信息
    const breadList = ref([{
        name: formName
    }])
	//权限验证
	const btnAuth = (e, a) => {
		return context?.$toolUtil.isAuth(e, a)
	}
	const list = ref([])
	const listLoading = ref(false)
	const listQuery = ref({
		page: 1,
		limit: 20,
		sort: 'addtime',
		order: 'desc'
	})
	const searchQuery = ref({})
	//分页
	const layouts = ref(["total","prev","pager","next","sizes","jumper"])
	const total = ref(0)
	const sizeChange = (size) => {
		listQuery.value.limit = size
		getList()
	}
	const currentChange = (page) => {
		listQuery.value.page = page
		getList()
	}
	//分页
	const searchClick = () => {
		listQuery.value.page = 1
		getList()
	}
	const getList = () => {
		listLoading.value = true
		let params = JSON.parse(JSON.stringify(listQuery.value))
		if (searchQuery.value.title && searchQuery.value.title != '') {
			params['title'] = `%${searchQuery.value.title}%`
		}
		context?.$http({
			url: `news/list`,
			method: 'get',
			params: params
		}).then(res => {
			listLoading.value = false
			list.value = res.data.data.list
			total.value = res.data.data.total
		})
	}
	//判断是否从个人中心跳转
	const centerType = ref(false)
	//返回
	const backClick = () => {
		router.push(`/index/${context?.$toolUtil.storageGet('frontSessionTable')}Center`)
	}
	const init = () => {
		if (route.query.centerType) {
			centerType.value = true
		}
		getList()
	}
	//定义弹窗
	const formModelRef = ref(null)
	const newsDetail = (id = null) => {
		if (id) {
			formModelRef.value.init(id)
		}
	}
	init()
</script>

<style lang="scss" scoped>
	// 返回盒子
	.back_view {
		border-radius: 4px;
		padding: 10px 0px;
		margin: 10px auto;
		background: none;
		width: 100%;
		text-align: right;
		// 返回按钮
		.back_btn {
			border: 1px solid var(--theme-color);
			cursor: pointer;
			border-radius: 0px;
			padding: 0 24px;
			color: #fff;
			background: var(--theme-color);
			width: auto;
			font-size: 14px;
			height: 34px;
		}
		// 返回按钮-悬浮
		.back_btn:hover {
		}
	}
	//搜索
	.list_search {
		.search_view {
			.search_label {
			}
			.search_box {
				// 输入框
				:deep(.search_inp) {

				}
			}
		}
		.search_btn_view {
			// 搜索按钮
			.search_btn {
			}
			// 搜索按钮-悬浮
			.search_btn:hover {
			}
		}
	}
	.news_list_one {
		.list-item {
			.news_img_box {
				.news_img {
				}
			}
			.news_content {
				.news_title {
				}
				.news_intro {
				}
				.news_time {
				}
			}
		}
	}
	// animation
	.animation_box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box:hover {
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.6s;
	}
	.animation_box img {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
		z-index: initial;
	}
	.animation_box img:hover {
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.6s;
	}
	// animation
	// 分页器
	.el-pagination {
		// 总页码
		:deep(.el-pagination__total) {
		}
		// 上一页
		:deep(.btn-prev) {
		}
		// 下一页
		:deep(.btn-next) {
		}
		// 上一页禁用
		:deep(.btn-prev:disabled) {
		}
		// 下一页禁用
		:deep(.btn-next:disabled) {
		}
		// 页码
		:deep(.el-pager) {
			// 数字
			.number {
			}
			// 数字悬浮
			.number:hover {
			}
			// 选中
			.number.is-active {
			}
		}
		// sizes
		:deep(.el-pagination__sizes) {
			.el-select {
				//去掉默认样式
				.select-trigger{
					height: 100%;
					.el-input{
						height: 100%;

					}
				}
			}
		}
		// 跳页
		:deep(.el-pagination__jump) {
			// 输入框
			.el-input {

			}
		}
	}
</style>
<style>
/* 总盒子*/
.news-page {
    width: 1200px;
    margin: 20px auto;
    padding: 20px 20px;
    background: #fff;
    overflow: hidden;
    font-size: 16px;
    display: flex;
    flex-wrap: wrap;
    align-items: flex-start;
}

/* 搜索 盒子*/
.news-page .list_search{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 20px auto;
    background: none;
}
.news-page .list_search .search_view{
    display: flex;
    align-items: center;
    justify-content: center;
    font-size:16px;
}
.news-page .list_search .search_view .search_label{
    white-space:nowrap;
}
.news-page .list_search .search_view .search_box .el-input .el-input__inner{
    border:1px solid var(--theme-color50);
    padding:0 10px;
    height:38px;
    line-height:38px;
    border-radius: 0px;
    min-width:500px;
}
.news-page .list_search .search_btn_view{
    margin-left:10px;
}
.news-page .list_search .search_btn_view .search_btn{
    min-width:80px;
    border:none;
    border-radius: 0px;
    font-size:15px;
    background: var(--theme-color);
}
.news-page .list_search .search_btn_view .search_btn:hover{
    opacity: 0.8;
}

/** 分类 总盒子 **/
.news-page .categorys{
    width:220px;
    display: flex;
    flex-wrap: wrap;
    background: none;
    padding: 0px;
    margin: 20px 20px 20px 0;
    border:1px solid var(--theme-color50);
    order:2;
}
/*item*/
.news-page .categorys .category{
    width:100%;
    padding: 0px;
    margin: 0 0 0px;
    box-sizing: border-box;
    background: rgba(255, 255, 255,1);
    cursor: pointer;
    border-bottom:1px solid var(--theme-color50);
    border-radius: 0px;
    color: rgb(51, 51, 51);
    text-align: center;
    line-height:50px;
}
.news-page .categorys .category:last-child{
    border-bottom:none;
}
/* 悬浮 */
.news-page .categorys .category:hover{
    background:var(--theme-color) !important;
    color: rgb(255, 255, 255) !important;
}
/* 选中 */
.news-page .categorys .category.active{
    background: var(--theme-color) !important;
    color: rgb(255, 255, 255) !important;
}
/** end **/

/* 样式一 盒子*/
.news-page .news_list_one{
    padding: 0 10px;
    width: calc(100% - 240px);
    margin: 20px 0 0;
    background:#fff;
    order:3;
    flex:1;
    display:flex;
    flex-wrap:wrap;
    justify-content:space-between;
}
.news-page .news_list_one .list-item{
    width: 48%;
    display: flex;
    flex-wrap:wrap;
    margin: 0px;
    height: auto;
    background: rgb(255, 255, 255);
    justify-content: space-between;
    padding:0px;
    cursor: pointer;
    background: none;
    border: 1px solid #eee;
    margin: 0 0 20px;
}
/* 图片盒子*/
.news-page .news_list_one .list-item .news_img_box{
    width: 100%;
    height: 250px;
    display: block;
    overflow:hidden;
}
.news-page .news_list_one .list-item .news_img_box .news_img{
    width: 100%;
    height: 100%;
    object-fit: cover;
    transition:all 0.6s;
}
.news-page .news_list_one .list-item:hover .news_img_box .news_img{
    transform:scale(1.05);
}

/* 内容盒子*/
.news-page .news_list_one .list-item .news_content{
    width: calc(100% - 0px);
    position: relative;
}
.news-page .news_list_one .list-item .news_content .news_title{
    padding: 0 10px;
    line-height: 40px;
    color: #333;
    font-size: 16px;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-weight: 500;
}
.news-page .news_list_one .list-item .news_content .news_intro{
    padding: 0 10px;
    line-height: 24px;
    color: rgb(102, 102, 102);
    font-size: 14px;
    height: 72px;
    overflow: hidden;
    text-indent: 2em;
    display: block;
}
.news-page .news_list_one .list-item .news_content .news_time{
    padding: 5px 10px;
    line-height: 21px;
    color: rgb(153, 153, 153);
    font-size: 15px;
    width: 100%;
    text-align: right;
    box-sizing: border-box;
    position: absolute;
    right: 0px;
    top: 5px;
}
/* 悬浮*/
.news-page .news_list_one .list-item:hover{
    color:var(--theme-color);
    border-color:var(--theme-color50);
}
.news-page .news_list_one .list-item:nth-child(2n){
}
.news-page .news_list_one .list-item:nth-child(2n):hover{
}
.news-page .news_list_one .list-item:hover .news_content .news_title{
    color:#9896f1;
}
.news-page .news_list_one .list-item:hover .news_content .news_intro{
    color:#9896f1;
}
.news-page .news_list_one .list-item:hover .news_content .news_time{
    color:#9896f1;
}

/* 详情盒子*/
.news-page .news_detail{
    width: 100%;
}
.news-page .news_detail .news_detail_title{
    font-size: 18px;
    font-weight: 500;
    color: rgb(0, 0, 0);
    text-align: center;
}
.news-page .news_detail .news_detail_time{
    text-align: right;
    font-size: 15px;
    color: #888;
    padding: 20px 0px 0px;
}
.news-page .news_detail .news_detail_content{
    margin-top:20px;
    text-align: left;
    font-size: 15px;
    color: #888;
    line-height:1.8;
    text-indent:2em;
}

/* 热门推荐 盒子*/
.news-page .hot_view{
    width:100%;
    margin: 0px auto 20px ;
    order:20;
}
/* 标题 */
.news-page .hot_view .hot_title{
    width: 101%;
    border-width: 0 0 1px;
    border-style: solid;
    border-color: var(--theme2-color);
    margin: 0 0 20px;
}
.news-page .hot_view .hot_title span{
    display:inline-block;
    font-size: 16px;
    color:#333;
    font-weight:600;
    padding-bottom:10px;
    border-bottom:2px solid var(--theme2-color);
}

.news-page .hot_view .hot_list{
    display: flex;
    flex-wrap:wrap;
    font-size:16px;
    margin: 0px;
    border:0px solid #eee;
    border-top:none;
    padding-top: 20px;
}
/* item*/
.news-page .hot_view .hot_list .hot{
    width: calc(25% - 20px);
    margin: 0px 10px 20px;
    background: none;
    padding: 0px;
    box-sizing: border-box;
    cursor: pointer;
}
.news-page .hot_view .hot_list .hot .hot_img{
    width:100%;
    height:150px;
    overflow:hidden;
}
.news-page .hot_view .hot_list .hot .hot_img .el-image__inner{
    width:100%;
    height:100%;
    object-fit:cover;
}
.news-page .hot_view .hot_list .hot .hot_content{

}
.news-page .hot_view .hot_list .hot .hot_content .hot_text{
    width:100%;
    white-space:nowrap;
    overflow:hidden;
    text-overflow:ellipsis;
    font-size:15px;
    color:#666;
    line-height:30px;
}
.news-page .hot_view .hot_list .hot .hot_content .hot_date{
    width:100%;
    line-height:24px;
    text-align: right;
    font-size:14px;
    color:#999;
}
/* 悬浮*/
.news-page .hot_view .hot_list .hot:hover{
}
.news-page .hot_view .hot_list .hot:hover .hot_content .hot_text{
}
.news-page .hot_view .hot_list .hot:hover .hot_content .hot_date{
}


</style>