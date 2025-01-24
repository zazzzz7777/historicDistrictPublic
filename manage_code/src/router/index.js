	import {
		createRouter,
		createWebHashHistory
	} from 'vue-router'
	import gongzuorenyuan from '@/views/gongzuorenyuan/list'
	import jingdianleixing from '@/views/jingdianleixing/list'
	import mianfeijingdian from '@/views/mianfeijingdian/list'
	import goupiaoxinxi from '@/views/goupiaoxinxi/list'
	import yuyuexinxi from '@/views/yuyuexinxi/list'
    import menu_manage from '@/views/menu_manage/list'
	import storeup from '@/views/storeup/list'
	import lishiwenhuajiequ from '@/views/lishiwenhuajiequ/list'
	import users from '@/views/users/list'
	import forum from '@/views/forum/list'
	import discusslishiwenhuajiequ from '@/views/discusslishiwenhuajiequ/list'
	import yonghu from '@/views/yonghu/list'
	import shoufeijingdian from '@/views/shoufeijingdian/list'
	import liuyanfankui from '@/views/liuyanfankui/list'
	import discussshoufeijingdian from '@/views/discussshoufeijingdian/list'
	import gonggaoxinxi from '@/views/gonggaoxinxi/list'
	import config from '@/views/config/list'
	import discussmianfeijingdian from '@/views/discussmianfeijingdian/list'
	import usersCenter from '@/views/users/center'
	import gongzuorenyuanRegister from '@/views/gongzuorenyuan/register'
	import gongzuorenyuanCenter from '@/views/gongzuorenyuan/center'

export const routes = [{
		path: '/login',
		name: 'login',
		component: () => import('../views/login.vue')
	},{
		path: '/',
		name: '首页',
		component: () => import('../views/index'),
		children: [{
			path: '/',
			name: '首页',
			component: () => import('../views/HomeView.vue'),
			meta: {
				affix: true
			}
		}, {
			path: '/updatepassword',
			name: '修改密码',
			component: () => import('../views/updatepassword.vue')
		}
		
		,{
			path: '/usersCenter',
			name: '管理员个人中心',
			component: usersCenter
		}
		,{
			path: '/gongzuorenyuanCenter',
			name: '工作人员个人中心',
			component: gongzuorenyuanCenter
		}
		,{
			path: '/gongzuorenyuan',
			name: '工作人员',
			component: gongzuorenyuan
		}
		,{
			path: '/jingdianleixing',
			name: '景点类型',
			component: jingdianleixing
		}
		,{
			path: '/mianfeijingdian',
			name: '免费景点',
			component: mianfeijingdian
		}
		,{
			path: '/goupiaoxinxi',
			name: '购票信息',
			component: goupiaoxinxi
		}
		,{
			path: '/yuyuexinxi',
			name: '预约信息',
			component: yuyuexinxi
		}
        ,{
            path: '/menu',
            name: '菜单权限管理',
            component: menu_manage
        }
		,{
			path: '/storeup',
			name: '我的收藏',
			component: storeup
		}
		,{
			path: '/lishiwenhuajiequ',
			name: '历史文化街区',
			component: lishiwenhuajiequ
		}
		,{
			path: '/users',
			name: '管理员',
			component: users
		}
		,{
			path: '/forum',
			name: '我的发布',
			component: forum
		}
		,{
			path: '/discusslishiwenhuajiequ',
			name: '历史文化街区评论-评论',
			component: discusslishiwenhuajiequ
		}
		,{
			path: '/yonghu',
			name: '用户',
			component: yonghu
		}
		,{
			path: '/shoufeijingdian',
			name: '收费景点',
			component: shoufeijingdian
		}
		,{
			path: '/liuyanfankui',
			name: '留言反馈',
			component: liuyanfankui
		}
		,{
			path: '/discussshoufeijingdian',
			name: '收费景点评论-评论',
			component: discussshoufeijingdian
		}
		,{
			path: '/gonggaoxinxi',
			name: '公告信息',
			component: gonggaoxinxi
		}
		,{
			path: '/config',
			name: '轮播图',
			component: config
		}
		,{
			path: '/discussmianfeijingdian',
			name: '免费景点评论-评论',
			component: discussmianfeijingdian
		}
		]
	},
	{
		path: '/gongzuorenyuanRegister',
		name: '工作人员注册',
		component: gongzuorenyuanRegister
	},
]

const router = createRouter({
	history: createWebHashHistory(process.env.BASE_URL),
	routes
})

export default router
