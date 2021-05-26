// pages/request/request.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  eventhandle: function (e) {
    console.info('表单提交携带数据', e.detail.value.username)
    console.info('表单提交携带数据', e.detail.value.password)
    if (e.detail.value.username.length > 0) {
      if (e.detail.value.password.length > 0) {
        wx.request({
          url: 'http://127.0.0.1:8081/school/login',
          method: "POST",
          data: {
            username: e.detail.value.username,
            password: e.detail.value.password
          },
          header: {
            "Content-Type": "application/x-www-form-urlencoded"
          },
          success(res) {
            console.log(res.data)
            if (res.data.code == 1) {
              // 服务器返回cookie中带着token，在本地存储，保持登录持久化
              if (res.header['Set-Cookie'] != '') {
                console.log(res.header['Set-Cookie'])
                //wx.setStorageSync('token', res.header['Set-Cookie'])
              }
              wx.showToast({
                title: res.data.msg,
                icon: 'success',
                duration: 1500
              })
              wx.switchTab({
                url: '../essay/essay',
              })
            } else {
              wx.showToast({
                title: res.data.msg, //这里打印出登录成功
                icon: 'error',
                duration: 1000
              })
            }
          }
        })
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.getStorage({
      key: 'sessionid',
      success: function (res) {
        // success
        that.setData({
          sessionid: res.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  erray: function () {

  },

  skipRegister: function () {
    wx.navigateTo({
      url: '../register/register',
    })
  }
})