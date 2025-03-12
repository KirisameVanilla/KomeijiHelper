<script setup>
import { ref, onMounted } from 'vue'
import userApi from "@/api/userApi.js";

// 定义选项数组
const options = ref([])

// 获取数据的异步函数
const fetchOptions = async () => {
  try {
    options.value = (await userApi.getUsersByUserClass(1)).data.data
  } catch (error) {
    console.error('获取选项失败:', error)
  }
}

// 页面加载时获取数据
onMounted(() => {
  fetchOptions()
})
</script>

<template>
  <div>
    <h2>请选择一个咨询师：</h2>
    <ul>
      <li v-for="(option, index) in options" :key="index">
        <label>
          <input type="radio" name="option" :value="option">
          {{ option }}
        </label>
      </li>
    </ul>
  </div>
</template>
