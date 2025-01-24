import { createRouter, createWebHashHistory } from 'vue-router'
import index from '../views'
import home from '../views/pages/home.vue'
import login from '../views/pages/login.vue'
import forumList from '@/views/pages/forum/list'
import yonghuList from '@/views/pages/yonghu/list'
import yonghuDetail from '@/views/pages/yonghu/formModel'
import yonghuAdd from '@/views/pages/yonghu/formAdd'
import yonghuRegister from '@/views/pages/yonghu/register'
import yonghuCenter from '@/views/pages/yonghu/center'
import gongzuorenyuanList from '@/views/pages/gongzuorenyuan/list'
import gongzuorenyuanDetail from '@/views/pages/gongzuorenyuan/formModel'
import gongzuorenyuanAdd from '@/views/pages/gongzuorenyuan/formAdd'
import jingdianleixingList from '@/views/pages/jingdianleixing/list'
import jingdianleixingDetail from '@/views/pages/jingdianleixing/formModel'
import jingdianleixingAdd from '@/views/pages/jingdianleixing/formAdd'
import shoufeijingdianList from '@/views/pages/shoufeijingdian/list'
import shoufeijingdianDetail from '@/views/pages/shoufeijingdian/formModel'
import shoufeijingdianAdd from '@/views/pages/shoufeijingdian/formAdd'
import storeupList from '@/views/pages/storeup/list'
import mianfeijingdianList from '@/views/pages/mianfeijingdian/list'
import mianfeijingdianDetail from '@/views/pages/mianfeijingdian/formModel'
import mianfeijingdianAdd from '@/views/pages/mianfeijingdian/formAdd'
import goupiaoxinxiList from '@/views/pages/goupiaoxinxi/list'
import goupiaoxinxiDetail from '@/views/pages/goupiaoxinxi/formModel'
import goupiaoxinxiAdd from '@/views/pages/goupiaoxinxi/formAdd'
import yuyuexinxiList from '@/views/pages/yuyuexinxi/list'
import yuyuexinxiDetail from '@/views/pages/yuyuexinxi/formModel'
import yuyuexinxiAdd from '@/views/pages/yuyuexinxi/formAdd'
import lishiwenhuajiequList from '@/views/pages/lishiwenhuajiequ/list'
import lishiwenhuajiequDetail from '@/views/pages/lishiwenhuajiequ/formModel'
import lishiwenhuajiequAdd from '@/views/pages/lishiwenhuajiequ/formAdd'
import liuyanfankuiList from '@/views/pages/liuyanfankui/list'
import liuyanfankuiDetail from '@/views/pages/liuyanfankui/formModel'
import liuyanfankuiAdd from '@/views/pages/liuyanfankui/formAdd'
import gonggaoxinxiList from '@/views/pages/gonggaoxinxi/list'
import gonggaoxinxiDetail from '@/views/pages/gonggaoxinxi/formModel'
import gonggaoxinxiAdd from '@/views/pages/gonggaoxinxi/formAdd'

const routes = [{
		path: '/',
		redirect: '/index/home'
	},
	{
		path: '/index',
		component: index,
		children: [{
			path: 'home',
			component: home
		}
		, {
			path: 'forumList',
			component: forumList
		}
		, {
			path: 'yonghuList',
			component: yonghuList
		}, {
			path: 'yonghuDetail',
			component: yonghuDetail
		}, {
			path: 'yonghuAdd',
			component: yonghuAdd
		}
		, {
			path: 'yonghuCenter',
			component: yonghuCenter
		}
		, {
			path: 'gongzuorenyuanList',
			component: gongzuorenyuanList
		}, {
			path: 'gongzuorenyuanDetail',
			component: gongzuorenyuanDetail
		}, {
			path: 'gongzuorenyuanAdd',
			component: gongzuorenyuanAdd
		}
		, {
			path: 'jingdianleixingList',
			component: jingdianleixingList
		}, {
			path: 'jingdianleixingDetail',
			component: jingdianleixingDetail
		}, {
			path: 'jingdianleixingAdd',
			component: jingdianleixingAdd
		}
		, {
			path: 'shoufeijingdianList',
			component: shoufeijingdianList
		}, {
			path: 'shoufeijingdianDetail',
			component: shoufeijingdianDetail
		}, {
			path: 'shoufeijingdianAdd',
			component: shoufeijingdianAdd
		}
        , {
            path: 'storeupList',
            component: storeupList
        }
		, {
			path: 'mianfeijingdianList',
			component: mianfeijingdianList
		}, {
			path: 'mianfeijingdianDetail',
			component: mianfeijingdianDetail
		}, {
			path: 'mianfeijingdianAdd',
			component: mianfeijingdianAdd
		}
		, {
			path: 'goupiaoxinxiList',
			component: goupiaoxinxiList
		}, {
			path: 'goupiaoxinxiDetail',
			component: goupiaoxinxiDetail
		}, {
			path: 'goupiaoxinxiAdd',
			component: goupiaoxinxiAdd
		}
		, {
			path: 'yuyuexinxiList',
			component: yuyuexinxiList
		}, {
			path: 'yuyuexinxiDetail',
			component: yuyuexinxiDetail
		}, {
			path: 'yuyuexinxiAdd',
			component: yuyuexinxiAdd
		}
		, {
			path: 'lishiwenhuajiequList',
			component: lishiwenhuajiequList
		}, {
			path: 'lishiwenhuajiequDetail',
			component: lishiwenhuajiequDetail
		}, {
			path: 'lishiwenhuajiequAdd',
			component: lishiwenhuajiequAdd
		}
		, {
			path: 'liuyanfankuiList',
			component: liuyanfankuiList
		}, {
			path: 'liuyanfankuiDetail',
			component: liuyanfankuiDetail
		}, {
			path: 'liuyanfankuiAdd',
			component: liuyanfankuiAdd
		}
		, {
			path: 'gonggaoxinxiList',
			component: gonggaoxinxiList
		}, {
			path: 'gonggaoxinxiDetail',
			component: gonggaoxinxiDetail
		}, {
			path: 'gonggaoxinxiAdd',
			component: gonggaoxinxiAdd
		}
		]
	},
	{
		path: '/login',
		component: login
	}
	,{
		path: '/yonghuRegister',
		component: yonghuRegister
	}
]

const router = createRouter({
  history: createWebHashHistory(process.env.BASE_URL),
  routes
})

export default router
