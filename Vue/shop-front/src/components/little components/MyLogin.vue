<template>
  <div>
    <div class="hello">
      <el-dialog :visible.sync="$store.state.showLogin" width="400px">
        <el-form class="Login"
                 ref="LoginForm"
                 v-loading="loading"
                 element-loading-text="正在登录..."
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0, 0, 0, 0.8)"
                 :model="LoginForm"
                 :rules="rules">
          <h3 style="  margin: 0 auto 40px auto;
  text-align: center;">系统登录</h3>
          <el-form-item prop="username" style="margin-bottom: 20px">
            <el-input size="small"
                      type="text"
                      prefix-icon="el-icon-user-solid"
                      v-model="LoginForm.username"
                      auto-complete="false"
                      placeholder="请输入账号">
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input size="small"
                      type="password"
                      prefix-icon="el-icon-key"
                      v-model="LoginForm.password"
                      auto-complete="false"
                      @keydown.enter.native="submitLogin"
                      placeholder="请输入密码">
            </el-input>
          </el-form-item>
          <el-form-item class="subRes">
            <el-button type="success" style="width: 100px" plain @click="submitLogin">登录</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>

import API from "@/utils/request";
import pubsub from "pubsub-js"

export default {
  name: "MyLogin",
  data() {
    return {
      LoginForm: {
        username: '',
        password: ''
      },
      formLabelWidth: '120px',
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur',}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      },
      loading: false,
      checked: true,
    }
  },
  methods: {
    submitLogin() {
      this.$refs.LoginForm.validate((valid) => {
        if (valid) {
          this.loading = true;//登录加载动画开启
          API.post('/api/user/login', this.LoginForm).then(resp => {
            if (resp.code === "0") {
              // console.log(resp.data)
              this.$notify({
                title: '成功',
                message: '这是一条成功的提示消息',
                type: 'success'
              });
              pubsub.publish("GetName", resp.data)
              const Info = JSON.stringify(resp.data);
              window.sessionStorage.setItem("Info", Info)
              this.$router.go(0)
            } else {
              this.$message.error("登录失败")
              this.loading = false;//登录加载动画关闭
            }
          })
        } else {
          this.$message.error('请输入所有字段！');
          return false;
        }
      });
      this.$store.state.showLogin = false;
    },
  }

}
</script>

<style scoped>
.hello {
  display: flex;
  justify-content: center;
  align-items: center;
}


.subRes {
  margin-top: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}

</style>
