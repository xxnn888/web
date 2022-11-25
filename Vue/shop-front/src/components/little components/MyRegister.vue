<template>
  <div>
    <el-dialog
        title="注册"
        :visible.sync="$store.state.showRegister"
        width="50%"
        center>
      <div>
        <el-form class="Regis"
                 ref="Regis"
                 :model="Regis"
                 v-loading="loading"
                 element-loading-text="正在登录..."
                 element-loading-spinner="el-icon-loading"
                 element-loading-background="rgba(0, 0, 0, 0.8)"
                 :rules="rules">
          <h3 style="  margin: 0 auto 40px auto;
  text-align: center;">系统注册</h3>
          <el-form-item prop="username" style="margin-bottom: 20px">
            <el-input size="small"
                      type="text"
                      prefix-icon="el-icon-user-solid"
                      v-model="Regis.username"
                      auto-complete="false"
                      placeholder="请输入账号">
            </el-input>
          </el-form-item>
          <el-form-item prop="phone">
            <el-input size="small"
                      type="text"
                      prefix-icon="el-icon-key"
                      v-model="Regis.phone"
                      auto-complete="false"
                      placeholder="请输入手机号">
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input size="small"
                      type="password"
                      prefix-icon="el-icon-key"
                      v-model="Regis.password"
                      auto-complete="false"
                      placeholder="请输入密码">
            </el-input>
          </el-form-item>
          <el-form-item prop="IsPassTrue">
            <el-input size="small"
                      type="password"
                      prefix-icon="el-icon-key"
                      v-model="IsPassTrue"
                      auto-complete="false"
                      @keydown.enter.native="SubmitRegister"
                      placeholder="请再次输入密码">
            </el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="RegistrOff">取 消</el-button>
        <el-button type="primary" @click=RegistrOn>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import API from "@/utils/request";

export default {
  name: "MyRegister",
  data() {
    return {
      Regis: {
        username: '',
        phone: "",
        password: '',
      },
      IsPassTrue: "",
      loading: false,
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        phone: [{required: true, message: "请输入手机号", trigger: "blur"}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      },
    }
  },
  methods: {
    RegistrOff() {
      this.$store.commit("setShowRegister", false)
    },
    RegistrOn() {
      if (this.Regis.password !== this.IsPassTrue) {
        this.$notify.error({
          title: '失败',
          message: '两次密码不一致',
          offset: 100
        });
      } else {
        this.$refs.Regis.validate((valid) => {
          if (valid) {
            API.post("api/user/register", this.Regis).then(resp => {
              if (resp.code === "0") {
                this.$notify({
                  title: '注册',
                  message: '注册成功',
                  type: "success",
                  offset: 100
                });
              } else {
                this.$notify.error({
                  title: '注册',
                  message: '注册失败',
                  offset: 100
                });
              }
            })
          } else {
            this.$notify.error({
              title: '失败',
              message: '请输入所有字段',
              offset: 100
            });
            return false;
          }
        })
      }
    },
    SubmitRegister() {
      console.log(2222)
    }
  }

}
</script>

<style scoped>

</style>