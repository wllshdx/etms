<template>
  <div class="department-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-fort-awesome"></i> 学院管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button @click="create" icon="el-icon-plus">创建</el-button>
          </el-col>

          <el-col :offset="13" :span="6">
            <el-input
              @keyup.enter.native="query"
              placeholder="学院名"
              v-model="queryForm.name"
            />
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary"
              >搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <el-row justify="center" type="flex">
        <el-pagination
          :current-page.sync="pageIndex"
          :page-size="pageSize"
          :total="pageSize * pageCount"
          @current-change="getPage"
          background
          layout="prev, pager, next"
        >
        </el-pagination>
      </el-row>

      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column label="学院号" prop="number" />
          <el-table-column label="学院名" prop="name" />
          <el-table-column label="地址" prop="address" />
          <el-table-column label="电话号码" prop="telephone" />
          <el-table-column label="专业数" prop="majorCount" />
          <el-table-column label="教师数" prop="teacherCount" />
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button @click="edit(scope.row.number)" size="mini" type="success"
                >编辑
              </el-button>
              <el-button
                @click="deleteItem(scope.row.number)"
                size="mini"
                type="danger"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <el-dialog :visible.sync="editing" title="编辑" width="30%">
        <el-form :model="entityForm" label-width="70px" ref="form">
          <el-form-item label="学院号">
            <el-input v-model="entityForm.number"></el-input>
          </el-form-item>
          <el-form-item label="学院名">
            <el-input v-model="entityForm.name"></el-input>
          </el-form-item>
          <el-form-item label="学院地址">
            <el-input v-model="entityForm.address"></el-input>
          </el-form-item>
          <el-form-item label="学院电话">
            <el-input v-model="entityForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span class="dialog-footer" slot="footer">
          <el-button @click="save" type="primary">确 定</el-button>
          <el-button @click="editing = false">取 消</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/department";

export default {
  name: "AdminDepartment",
  data() {
    return {
      queryForm: {
        name: "",
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      isCreate: false,
    };
  },
  methods: {
    query() {
      api.getPageCount(this.queryForm.name).then((res) => {
        this.pageCount = res;
        this.pageIndex = 1;
        this.getPage(1);
      });
    },
    getPage(pageIndex) {
      api.getPage(pageIndex, this.queryForm.name).then((res) => {
        this.tableData = res;
      });
    },
    create() {
      this.entityForm = {
        id: -1,
        number: "",
        name: "",
        address: "",
        telephone: ""
      };
      this.isCreate = true;
      this.editing = true;
    },
    edit(number) {
      api.get(number).then((res) => {
        this.entityForm = res;
        this.editing = true;
      });
    },
    save() {
      if (this.isCreate === true) {
        api.create(this.entityForm).then(() => {
          this.finishSave();
          this.isCreate = false;
        });
      } else {
        api.update(this.entityForm).then(() => {
          this.finishSave();
        });
      }
    },
    finishSave() {
      this.$message.success("成功");
      this.getPage(this.pageIndex);
      this.editing = false;
    },
    deleteItem(number) {
      api.deleteItem(number).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
      });
    },
  },
  created() {
    this.query();
  },
};
</script>

<style scoped></style>
