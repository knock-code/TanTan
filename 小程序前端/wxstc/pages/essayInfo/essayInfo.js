// pages/essayInfo/essayInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    info:{},
    //文章作者

    //文章评论
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var info = JSON.parse(options.info)
    // 根据文章id查询文章的作者和评论列表
    wx.request({
      url: 'http://127.0.0.1:8081/school/essay/selectEssayComment?essay_id='+info.essay_id,
      header:{
        'content-type': 'application/json'
      },
      method:"GET",
      success(res){
        that.setData({
          info:res.data
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

  }
})