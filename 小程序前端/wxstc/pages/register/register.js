//获取应用实例
var app = getApp()
var imag
Page({
  //data定义页面数据
  data: {
    userInfo: "../../images/me_other/shezhi.png",
    showModalStatus: false,
    title: '注册页面',
    isLogin: false
  },
  onRegister(e){
    console.info('表单提交携带数据', e.detail.value.number)
    console.info('表单提交携带数据', e.detail.value.password)
    console.info('表单提交携带数据', e.detail.value.studentId)
    if(e.detail.value.number.length > 0){
      if(e.detail.value.password.length > 0){
        if(e.detail.value.studentId.length > 0){
          wx.request({
            url: 'http://127.0.0.1:8081/school/register',
            method: "POST",
            data:{
              username:e.detail.value.number,
              password:e.detail.value.password,
              studentId:e.detail.value.studentId
            },
            header:{
              "Content-Type": "application/x-www-form-urlencoded"
            },
            success(res){
              console.log(res.data)
              if(res.data.code == 1){
                wx.showToast({
                  title: res.data.msg,
                  icon: 'success',
                  duration: 3500
                })
                setTimeout( function(){
                  //5秒后实现的方法写在这个方法里面
                  wx.navigateBack({
                    delta: 1,
                  })
                  }, 1000 );
              }else{
                wx.showToast({
                  title: res.data.msg,//这里打印出登录成功
                  icon: 'error',
                  duration: 1000
                })
              }
            }
          })
        } else {
          wx.showToast({
            title: '请输入学号！',
            icon: 'error',
            duration: 1500
          })
        }
      } else {
        wx.showToast({
          title: '请输入密码！',
          icon: 'error',
          duration: 1500
        })
      }
    } else {
        wx.showToast({
          title: '请输入账号!',
          icon: 'error',
          duration: 1500
        })
    }
  },
  changeAvatar() {
    
    var that = this
    wx.showActionSheet({
      itemList: ["从相册选择", "拍照"],
      //点击选项按钮触发的事件
      success: function (res) {
        console.log("*********************************"+res.tapIndex)
        that.myChooseImg(res.tapIndex)
        .then((tempFilePaths)=>{
          imag = tempFilePaths
          this.onLoad()
          console.log("##################"+tempFilePaths)
        
          //调用setData()给变量赋新值，因为这样才能让页面刷新，渲染新数据
          that.setData({
            userInfo: info
          })
        })
        .catch(()=>{
        })
      },
      //点击取消
      fail: function (res) {
        console.log('xxxxxxx')
      }
    })
  },

  //选取照片
  myChooseImg(index) {
    let that = this
    let sourceType = null
    if (index == 0) {
      //相册
      sourceType = ['album']
    }
    else {
      //拍照
      sourceType = ['camera']
    }
    return new Promise((resolve, reject) => {
      wx.chooseImage({
        // 默认9
        count: 1,
        // 可以指定是原图还是压缩图，默认二者都有
        sizeType: ['original', 'compressed'],
        // 可以指定来源是相册还是相机，默认二者都有
        sourceType: sourceType,
        // 返回选定照片的本地文件路径列表，tempFilePath可以作为img标签的src属性显示图片
        success: function (res) {
          //获取到的图片路径DataURL
          var tempFilePaths = res.tempFilePaths
          console.log(tempFilePaths)
          if(tempFilePaths){
            resolve(tempFilePaths)
          }
          else{
            reject()
          }
        }
      })
    })
  },
  //页面加载完成事件
  onLoad: function () {
    console.log('onLoad')
    var that = this
    that.setData({
      userInfo: imag
    })
    //调用应用实例的方法获取全局数据
    // app.getUserInfo(function (){
    //   console.log(userInfo)
    //   that.setData({
    //     userInfo: "../../images/me_other/shezhi.png"
    //   })
    // })
  }
})
