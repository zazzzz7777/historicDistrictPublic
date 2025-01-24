const config = {
    get() {
        return {
            url : process.env.VUE_APP_BASE_API_URL + process.env.VUE_APP_BASE_API + '/',
            name: process.env.VUE_APP_BASE_API,
			menuList:[
				{
					name: '历史文化街区管理',
					icon: '',
					child:[

						{
							name:'历史文化街区',
							url:'/index/lishiwenhuajiequList'
						},
					]
				},
				{
					name: '景点',
					icon: '',
					child:[

						{
							name:'收费景点',
							url:'/index/shoufeijingdianList'
						},

						{
							name:'免费景点',
							url:'/index/mianfeijingdianList'
						},
					]
				},
				{
					name: '论坛交流',
					icon: 'icon-common43',
					child:[

						{
							name:'论坛交流',
							url:'/index/forumList'
						},
					]
				},
			]
        }
    },
    getProjectName(){
        return {
            projectName: "基于 SpringBoot 的中国历史文化街区管理系统"
        } 
    }
}
export default config
