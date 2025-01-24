<template>
	<div>
		<el-dialog modal-class="edit_form_modal" class="edit_form" v-model="formVisible" :title="formTitle" width="80%" destroy-on-close :fullscreen='false'>
			<el-form class="formModel_form" ref="formRef" :model="form" :rules="rules">
				<el-row>
					<el-col :span="12">
						<el-form-item prop="jingdiantupian"
									  label="景点图片"
						>
							<uploads
								:disabled="!isAdd||disabledForm.jingdiantupian?true:false"
								action="file/upload"

								tip="请上传景点图片"
								:limit="3"
								style="width: 100%;text-align: left;"
								:fileUrls="form.jingdiantupian?form.jingdiantupian:''" 
								@change="jingdiantupianUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="街区名称" prop="jiequmingcheng">
							<el-input class="list_inp" v-model="form.jiequmingcheng" placeholder="街区名称"
								 type="text" 								:readonly="!isAdd||disabledForm.jiequmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="景点名称" prop="jingdianmingcheng">
							<el-input class="list_inp" v-model="form.jingdianmingcheng" placeholder="景点名称"
								 type="text" 								:readonly="!isAdd||disabledForm.jingdianmingcheng?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="12">
						<el-form-item label="景点类型" prop="jingdianleixing">
							<el-select
								class="list_sel"
								:disabled="!isAdd||disabledForm.jingdianleixing?true:false"
								v-model="form.jingdianleixing" 
								placeholder="请选择景点类型"
								>
								<el-option v-for="(item,index) in jingdianleixingLists" :label="item"
									:value="item"
									>
								</el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="视频" prop="shipin">
							<uploads
								:disabled="!isAdd||disabledForm.shipin?true:false"
								type="file"
								action="file/upload" 
								tip="请上传视频" 
								:limit="1" 
								style="width: 100%;text-align: left;"
								:fileUrls="form.shipin?form.shipin:''" 
								@change="shipinUploadSuccess">
							</uploads>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="开放时间" prop="kaifangshijian">
							<el-date-picker
								class="list_date"
								v-model="form.kaifangshijian"
								format="YYYY-MM-DD HH:mm:ss"
								value-format="YYYY-MM-DD HH:mm:ss"
								type="datetime"
								:readonly="!isAdd||disabledForm.kaifangshijian?true:false"
								placeholder="请选择开放时间" />
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="地址" prop="dizhi">
							<el-input class="list_inp" v-model="form.dizhi" placeholder="地址"
								 type="text" 								:readonly="!isAdd||disabledForm.dizhi?true:false" />
						</el-form-item>
					</el-col>

					<el-col :span="24">
						<el-form-item label="介绍" prop="jieshao">
							<editor :value="form.jieshao" placeholder="请输入介绍" :readonly="!isAdd||disabledForm.jieshao?true:false"
								class="list_editor" @change="(e)=>editorChange(e,'jieshao')"></editor>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<template #footer v-if="isAdd||type=='logistics'||type=='reply'">
				<span class="formModel_btn_box">
					<el-button class="cancel_btn" @click="closeClick">取消</el-button>
					<el-button class="confirm_btn" type="primary" @click="save"
						>
						提交
					</el-button>
				</span>
			</template>
		</el-dialog>
	</div>
</template>
<script setup>
	import {
		reactive,
		ref,
		getCurrentInstance,
		nextTick,
		computed,
		defineEmits
	} from 'vue'
    import {
        useStore
    } from 'vuex';
    const store = useStore()
    const user = computed(()=>store.getters['user/session'])
	const context = getCurrentInstance()?.appContext.config.globalProperties;	
	const emit = defineEmits(['formModelChange'])
	//基础信息
	const tableName = 'mianfeijingdian'
	const formName = '免费景点'
	//基础信息
	//form表单
	const form = ref({})
	const disabledForm = ref({
		jingdiantupian : false,
		jiequmingcheng : false,
		jingdianmingcheng : false,
		jingdianleixing : false,
		shipin : false,
		kaifangshijian : false,
		dizhi : false,
		jieshao : false,
		thumbsupNumber : false,
		crazilyNumber : false,
		storeupNumber : false,
		discussNumber : false,
		clickNumber : false,
		sfsh : false,
		shhf : false,
	})
	const formVisible = ref(false)
	const isAdd = ref(false)
	const formTitle = ref('')
	//表单验证
	//匹配整数
	const validateIntNumber = (rule, value, callback) => {
		if (!value) {
			callback();
		} else if (!context?.$toolUtil.isIntNumer(value)) {
			callback(new Error("请输入整数"));
		} else {
			callback();
		}
	}
	//匹配数字
	const validateNumber = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isNumber(value)) {
			callback(new Error("请输入数字"));
		} else {
			callback();
		}
	}
	//匹配手机号码
	const validateMobile = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isMobile(value)) {
			callback(new Error("请输入正确的手机号码"));
		} else {
			callback();
		}
	}
	//匹配电话号码
	const validatePhone = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isPhone(value)) {
			callback(new Error("请输入正确的电话号码"));
		} else {
			callback();
		}
	}
	//匹配邮箱
	const validateEmail = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isEmail(value)) {
			callback(new Error("请输入正确的邮箱地址"));
		} else {
			callback();
		}
	}
	//匹配身份证
	const validateIdCard = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.checkIdCard(value)) {
			callback(new Error("请输入正确的身份证号码"));
		} else {
			callback();
		}
	}
	//匹配网站地址
	const validateUrl = (rule, value, callback) => {
		if(!value){
			callback();
		} else if (!context?.$toolUtil.isURL(value)) {
			callback(new Error("请输入正确的URL地址"));
		} else {
			callback();
		}
	}
	const rules = ref({
		jingdiantupian: [
		],
		jiequmingcheng: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		jingdianmingcheng: [
		],
		jingdianleixing: [
		],
		shipin: [
		],
		kaifangshijian: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		dizhi: [
			{required: true,message: '请输入',trigger: 'blur'}, 
		],
		jieshao: [
		],
		thumbsupNumber: [
			{ validator: validateIntNumber, trigger: 'blur' },
		],
		crazilyNumber: [
			{ validator: validateIntNumber, trigger: 'blur' },
		],
		storeupNumber: [
			{ validator: validateIntNumber, trigger: 'blur' },
		],
		discussNumber: [
			{ validator: validateIntNumber, trigger: 'blur' },
		],
		clickNumber: [
			{ validator: validateIntNumber, trigger: 'blur' },
		],
		sfsh: [
		],
		shhf: [
		],
	})
	//表单验证
	
	const formRef = ref(null)
	const id = ref(0)
	const type = ref('')
	//景点图片上传回调
	const jingdiantupianUploadSuccess=(e)=>{
		form.value.jingdiantupian = e
	}
	//景点类型列表
	const jingdianleixingLists = ref([])
	//视频上传回调
	const shipinUploadSuccess=(e)=>{
		form.value.shipin = e
	}

	//获取唯一标识
	const getUUID =()=> {
      return new Date().getTime();
    }
	//重置
	const resetForm = () => {
		form.value = {
			jingdiantupian: '',
			jiequmingcheng: '',
			jingdianmingcheng: '',
			jingdianleixing: '',
			shipin: '',
			kaifangshijian: '',
			dizhi: '',
			jieshao: '',
			thumbsupNumber: '',
			crazilyNumber: '',
			storeupNumber: '',
			discussNumber: '',
			clickNumber: '',
			shhf: '',
		}
	}
	//获取info
	const getInfo = ()=>{
		context?.$http({
			url: `${tableName}/info/${id.value}`,
			method: 'get'
		}).then(res => {
			let reg=new RegExp('../../../file','g')
			res.data.data.jieshao = res.data.data.jieshao?(res.data.data.jieshao.replace(reg,'../../../historicDistrict/file')):'';
			form.value = res.data.data
			formVisible.value = true
		})
	}
	const crossRow = ref('')
	const crossTable = ref('')
	const crossTips = ref('')
	const crossColumnName = ref('')
	const crossColumnValue = ref('')
	//初始化
	const init=(formId=null,formType='add',formNames='',row=null,table=null,statusColumnName=null,tips=null,statusColumnValue=null)=>{
		resetForm()
		if(formId){
			id.value = formId
			type.value = formType
		}
		if(formType == 'add'){
			isAdd.value = true
			formTitle.value = '新增' + formName
			formVisible.value = true
		}else if(formType == 'info'){
			isAdd.value = false
			formTitle.value = '查看' + formName
			getInfo()
		}else if(formType == 'edit'){
			isAdd.value = true
			formTitle.value = '修改' + formName
			getInfo()
		}
		else if(formType == 'cross'){
			isAdd.value = true
			formTitle.value = formNames
			// getInfo()
			for(let x in row){
				if(x=='jingdiantupian'){
					form.value.jingdiantupian = row[x];
					disabledForm.value.jingdiantupian = true;
					continue;
				}
				if(x=='jiequmingcheng'){
					form.value.jiequmingcheng = row[x];
					disabledForm.value.jiequmingcheng = true;
					continue;
				}
				if(x=='jingdianmingcheng'){
					form.value.jingdianmingcheng = row[x];
					disabledForm.value.jingdianmingcheng = true;
					continue;
				}
				if(x=='jingdianleixing'){
					form.value.jingdianleixing = row[x];
					disabledForm.value.jingdianleixing = true;
					continue;
				}
				if(x=='shipin'){
					form.value.shipin = row[x];
					disabledForm.value.shipin = true;
					continue;
				}
				if(x=='kaifangshijian'){
					form.value.kaifangshijian = row[x];
					disabledForm.value.kaifangshijian = true;
					continue;
				}
				if(x=='dizhi'){
					form.value.dizhi = row[x];
					disabledForm.value.dizhi = true;
					continue;
				}
				if(x=='jieshao'){
					form.value.jieshao = row[x];
					disabledForm.value.jieshao = true;
					continue;
				}
				if(x=='thumbsupNumber'){
					form.value.thumbsupNumber = row[x];
					disabledForm.value.thumbsupNumber = true;
					continue;
				}
				if(x=='crazilyNumber'){
					form.value.crazilyNumber = row[x];
					disabledForm.value.crazilyNumber = true;
					continue;
				}
				if(x=='storeupNumber'){
					form.value.storeupNumber = row[x];
					disabledForm.value.storeupNumber = true;
					continue;
				}
				if(x=='discussNumber'){
					form.value.discussNumber = row[x];
					disabledForm.value.discussNumber = true;
					continue;
				}
				if(x=='clickNumber'){
					form.value.clickNumber = row[x];
					disabledForm.value.clickNumber = true;
					continue;
				}
			}
			if(row){
				crossRow.value = row
			}
			if(table){
				crossTable.value = table
			}
			if(tips){
				crossTips.value = tips
			}
			if(statusColumnName){
				crossColumnName.value = statusColumnName
			}
			if(statusColumnValue){
				crossColumnValue.value = statusColumnValue
			}
			formVisible.value = true
		}

		context?.$http({
			url: `${context?.$toolUtil.storageGet('sessionTable')}/session`,
			method: 'get'
		}).then(res => {
			var json = res.data.data
		})
		context?.$http({
			url: `option/jingdianleixing/jingdianleixing`,
			method: 'get'
		}).then(res=>{
			jingdianleixingLists.value = res.data.data
		})
	}
	//初始化
	//声明父级调用
	defineExpose({
		init
	})
	//关闭
	const closeClick = () => {
		formVisible.value = false
	}
	//富文本
	const editorChange = (e,name) =>{
		form.value[name] = e
	}
	//提交
	const save= async ()=>{
		if(form.value.jingdiantupian!=null) {
			form.value.jingdiantupian = form.value.jingdiantupian.replace(new RegExp(context?.$config.url,"g"),"");
		}
		if(form.value.shipin!=null) {
			form.value.shipin = form.value.shipin.replace(new RegExp(context?.$config.url,"g"),"");
		}
		var table = crossTable.value
		var objcross = JSON.parse(JSON.stringify(crossRow.value))
		let crossUserId = ''
		let crossRefId = ''
		let crossOptNum = ''
		if(type.value == 'cross'){
			if(crossColumnName.value!=''){
				if(!crossColumnName.value.startsWith('[')){
					for(let o in objcross){
						if(o == crossColumnName.value){
							objcross[o] = crossColumnValue.value
						}
					}
					//修改跨表数据
					changeCrossData(objcross)
				}else{
					crossUserId = user.value.id
					crossRefId = objcross['id']
					crossOptNum = crossColumnName.value.replace(/\[/,"").replace(/\]/,"")
				}
			}
		}
		formRef.value.validate((valid)=>{
			if(valid){
				if(crossUserId&&crossRefId){
					form.value.crossuserid = crossUserId
					form.value.crossrefid = crossRefId
					let params = {
						page: 1,
						limit: 1000, 
						crossuserid:form.value.crossuserid,
						crossrefid:form.value.crossrefid,
					}
					context?.$http({
						url: `${tableName}/page`,
						method: 'get', 
						params: params 
					}).then(res=>{
						if(res.data.data.total>=crossOptNum){
							context?.$toolUtil.message(`${crossTips.value}`,'error')
							return false
						}else{
							context?.$http({
								url: `${tableName}/${!form.value.id ? "save" : "update"}`,
								method: 'post', 
								data: form.value 
							}).then(async res=>{
								emit('formModelChange')
								context?.$toolUtil.message(`操作成功`,'success')
                                formVisible.value = false
							})
						}
					})
				}else{
					context?.$http({
						url: `${tableName}/${!form.value.id ? "save" : "update"}`,
						method: 'post', 
						data: form.value 
					}).then(async (res)=>{
						emit('formModelChange')
						context?.$toolUtil.message(`操作成功`,'success')
                        formVisible.value = false
					})
				}
			}else{
                context.$message.error('请完善信息')
            }
		})
	}
	//修改跨表数据
	const changeCrossData = async (row)=>{
        if(type.value == 'cross'){
            await context?.$http({
                url: `${crossTable.value}/update`,
                method: 'post',
                data: row
            }).then(res=>{})
        }
	}
</script>
<style lang="scss" scoped>
	// 表单
	.formModel_form{
		// form item
		:deep(.el-form-item) {
			//label
			.el-form-item__label {
			}
			// 内容盒子
			.el-form-item__content {
				// 输入框
				.list_inp {
				}
				//日期选择器
				.list_date {
				}
				// 下拉框
				.list_sel {
					//去掉默认样式
					.select-trigger{
						height: 100%;
						.el-input{
							height: 100%;
						}
					}
				}
				// 富文本
				.list_editor {
				}
				//图片上传样式
				.el-upload-list  {
					//提示语
					.el-upload__tip {
					}
					//外部盒子
					.el-upload--picture-card {
						//图标
						.el-icon{
						}
					}
					.el-upload-list__item {
					}
				}
				//文件上传样式
				.upload-demo {
					width: 100%;
					//外部盒子
					.el-upload-dragger {
					}
					//图标
					.el-icon--upload {
					}
					//提示文字
					.el-upload__text {
						em {
						}
					}
					//提示文字
					.el-upload__tip {
					}
				}
			}
		}
	}
	// 按钮盒子
	.formModel_btn_box {
		.cancel_btn {
		}
		.cancel_btn:hover {
		}
		
		.confirm_btn {
		}
		.confirm_btn:hover {
		}
	}
</style>
