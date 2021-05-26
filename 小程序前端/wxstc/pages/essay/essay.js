// pages/essay/essay.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    listData: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
   wx.request({
     url: 'http://127.0.0.1:8081/school/essay/selectAllEssay',
     method: 'GET',
     header: {
      'cookie': wx.getStorageSync('token')
     },
     success: res => {
      console.log(res.data)
      this.setData({
        //第一个data为固定用法
        listData: res.data 
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
  // console.log('reach bottom')
  //   var arr = this.data.listData;
  //   arr.push(
  //     {name: "新的", img: "http://images.china.cn/attachement/jpg/site1000/20170928/f44d307d90e51b3712d33d.jpg",
  //     info: "2021.03.25"},
  //     {name: "新的", img: "http://www.xinhuanet.com/2021-03/22/1127242196_16164170519131n.jpg",
  //     info: "2021.03.25"},
  //     {name: "新的", img: "http://www.xinhuanet.com/multimediapro/2021-03/23/1211079674_16164799728941n.jpg",
  //     info: "2021.03.25"},
  //     {name: "新的", img: "http://images.china.cn/attachement/jpg/site1000/20170928/f44d307d90e51b3712d33d.jpg",
  //     info: "2021.03.25"}
  //   );
  //   this.setData({
  //     listData:arr
  //   })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  /**
   * 新闻label展示事件
   */
  clickEssay: function(e){
    var info = JSON.stringify(e.currentTarget.dataset.essayinfo)
    var state = wx.getStorageSync('token');
    if(state != ''){
      console.log("成辉" + info)
      wx.navigateTo({
        url: '../essayInfo/essayInfo?info=' + info
      })
    } else {
      wx.showToast({
        title: '您未登录!',
        icon: 'error',
        duration: 1500
      })
    }
  }
})