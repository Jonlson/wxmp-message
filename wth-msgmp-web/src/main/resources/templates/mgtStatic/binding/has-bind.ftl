<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
  <link rel="stylesheet" href="${springUrl}css/style.css">
  <script type="text/javascript" src="${springUrl}js/jquery.min.js" charset="utf-8"></script>
  <title>绑定手机号</title>
</head>
<body>
  <section class="phone-card">
    <p class="h1-title">您当前绑定通知手机号码</p>
    <p id="phone" class="h1-title current-phone"></p>
    <input id = "_id" style="display: none;">
    <input id = "openId" style="display: none;">
    <p class="sub-title">您的智能柜推送信息将通过本微信公众号推送</p>
  </section>
  <p>
    <button class="btn active" style="margin-top: 3rem;" id="editPhone">修改绑定手机号</button>
  </p>
  <p>
    <button class="btn active-color" style="margin-top: 3rem;" id="cancelBind">取消绑定手机号</button>
  </p>

  <section class="mask" id="mask">

  </section>

  <section class="confirm" id="confirm">
    <section class="content">
      <p class="unbind-tip">确定解除手机绑定</p>
      <section class="bottom">
        <button class="btn cancel" id="cancel">取消</button>
        <button class="btn ok" id="ok">确定</button>
      </section>
    </section>
  </section>

  <script type="text/javascript">
    window.onload = function () {
      var phoneEle = document.getElementById('phone')
      document.getElementById('_id').value='${WechatMpUser.id}'
      document.getElementById('openId').value='${WechatMpUser.openId}'
      setPhone('${WechatMpUser.phone}')
      function setPhone (phone) {
        var reg = /^1[3-9]\d{9}$/
        if (reg.test(phone)) {
          var str = phone.substring(0, 3) + ' ' + phone.substring(3, 7) + ' ' + phone.substring(7, 11)
          phoneEle.innerText = str
        }
      }
      document.body.addEventListener('click', function (e) {
        var id = e.target.id
        switch (id) {
          // 修改绑定手机号
          case 'editPhone':
            window.location.href = '${springUrl}_wx/phoneBinding?id='+document.getElementById('_id').value+'&openId='+document.getElementById('openId').value
            break;
          // 取消绑定手机号
          case 'cancelBind':
            toggleConfirm(true,'cancelBind')
            break;
          // 取消取消绑定
          case 'cancel':
            toggleConfirm(false,'cancel')
            break;
          // 确定取消绑定
          case 'ok':
            // 调用解除绑定手机号的接口
            toggleConfirm(false,'ok')
            break;
        }
      })

      function toggleConfirm (flag,type) {

        var mask = document.getElementById('mask')
        var confirm = document.getElementById('confirm')
        if (flag) {
          mask.style.display = 'block'
          confirm.style.display = 'flex'
        } else {
          mask.style.display = 'none'
          confirm.style.display = 'none'
        }
        //取消手机号绑定
        if (!flag && type == "ok"){
          $.ajax({
            type: 'get',
            url: '${springUrl}_wx/deleted?id='+document.getElementById('_id').value,
            cache: false,
            dataType: "json",
            success: function(result) {
              if(result.code == 0){
                window.location.href = '${springUrl}_wx/phoneBinding?openId='+document.getElementById('openId').value
              }

            }
          });

        }
      }
    }
  </script>
</body>
</html>