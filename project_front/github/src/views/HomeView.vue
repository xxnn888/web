<template>
  <div class="home">
    <HelloWorld/>
    <div>
      <el-table
          :data="user"
          border
          style="width: 100%">
        <el-table-column
            fixed
            prop="id"
            label="编号"
            width="50">
        </el-table-column>
        <el-table-column
            prop="name"
            label="姓名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="username"
            label="笔名"
            width="150">
        </el-table-column>
        <el-table-column
            prop="email"
            label="邮箱地址"
            width="200">
        </el-table-column>
        <el-table-column
            prop="address.city"
            label="所在城市"
            width="130">
        </el-table-column>
        <el-table-column
            prop="address.street"
            label="所在街道"
            width="160">
        </el-table-column>
        <el-table-column
            prop="company.name"
            label="公司名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="phone"
            label="电话号码"
            width="200">
        </el-table-column>
        <el-table-column
            prop="website"
            label="网址"
            width="120">
        </el-table-column>
        <el-table-column
            prop="company.catchPhrase"
            label="公司口号"
            width="260">
        </el-table-column>
        <el-table-column fixed label="操作" width="150px">
          <template slot-scope="scope">
            <el-button size="mini" @click="dialogVisible = true">编辑</el-button>
            <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-dialog
          title="编辑信息"
          :visible.sync="dialogVisible"
          width="80%">
        <div class="form">
          <el-form :inline="true" :model="bossUser" ref="empForm" :rules="rules">
            <el-row>
              <el-col :span="8">
                <el-form-item label="姓名" prop="user">
                  <el-input v-model="bossUser.user" placeholder="请输入姓名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="笔名" prop="username">
                  <el-input v-model="bossUser.username" placeholder="请输入笔名"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="邮箱地址" prop="email">
                  <el-input v-model="bossUser.email" placeholder="请输入邮箱地址"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="所在城市" prop="city">
                  <el-input v-model="bossUser.address.city" placeholder="请输入所在城市"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="所在街道" prop="street">
                  <el-input v-model="bossUser.address.street" placeholder="请输入所在街道"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="公司名" prop="companyName">
                  <el-input v-model="bossUser.company.name" placeholder="请输入公司名"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="8">
                <el-form-item label="公司电话号码" prop="phone">
                  <el-input v-model="bossUser.phone" placeholder="请输入公司电话号码"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="网址" prop="website">
                  <el-input v-model="bossUser.website" placeholder="请输入网址"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="公司口号" prop="catchPhrase">
                  <el-input v-model="bossUser.company.catchPhrase" placeholder="请输入公司口号"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
            </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>

import HelloWorld from '@/components/HelloWorld.vue'
import {initU} from "@/utils/user";
export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data() {
    return {
      dialogVisible: false,
      bossUser: {
        name: '',
        username: '',
        email: '',
        address: {
          street: '',
          city: '',

        },
        phone: '',
        website: '',
        company: {
          name: '',
          catchPhrase: '',
        }
      },
      rules: {
        user: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        username: [{required: true, message: '请输入笔名', trigger: 'blur'}],
        email: [{required: true, message: '请输入邮箱地址', trigger: 'blur'}, {
          type: 'email',
          message: '邮箱格式不正确',
          trigger: 'blur'
        }],
        city: [{required: true, message: '请输入所在城市', trigger: 'blur'}],
        street: [{required: true, message: '请输入街道', trigger: 'blur'}],
        companyName: [{required: true, message: '请输入公司名', trigger: 'blur'}],
        phone: [{required: true, message: '请输入电话号码', trigger: 'blur'}, {
          pattern: '^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$',
          message: '电话号码不正确',
          trigger: 'blur'
        }],
        catchPhrase: [{required: true, message: '请输入公司口号', trigger: 'blur'}],
        website: [{required: true, message: '请输入网站', trigger: 'blur'}],

      }
    };
  },
  computed: {
    user() {
      return this.$store.state.user
    }
  },
  methods: {},
  mounted() {
    initU()
  }
// mounted() {
//   axios.get('http://jsonplaceholder.typicode.com/users').then(resp => {
//     console.log(resp.data)
//     window.sessionStorage.setItem('userInfo', JSON.stringify(resp.data))
//   }, error => {
//     console.log(error.message)
//   })
// }

}
</script>
<style>
/*.form el-form-item {*/
/*  display: inline-block;*/
/*  width: 200px;*/
/*  float: left;*/
/*  margin-left: 10px;*/
/*}*/
</style>
