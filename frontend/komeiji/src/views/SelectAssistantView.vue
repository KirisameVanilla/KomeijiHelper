<script setup>
import { ref, onMounted } from 'vue'
import userApi from "@/api/userApi.js";

const options = ref([])          // 存放选项数组
const selectedOption = ref(null) // 存放用户选择的选项

// 获取选项数据
const fetchOptions = async () => {
  try {
    options.value = (await userApi.getUsersByUserClass(1)).data.data
  } catch (error) {
    console.error('获取选项失败:', error)
  }
}

// 提交选中的咨询师
const submitOption = async () => {
  if (!selectedOption.value) {
    alert('请选择一个咨询师')
    return
  }

  try {
    const response = await userApi.submitConsultant({
      consultantName: selectedOption.value
    })

    if (response.data.code === 200) {
      alert('提交成功！')
    } else {
      alert('提交失败，请重试')
    }
  } catch (error) {
    console.error('提交选项失败:', error)
    alert('提交失败，请检查网络')
  }
}

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
          <input
              type="radio"
              name="option"
              :value="option"
              v-model="selectedOption"
          >
          {{ option }}
        </label>
      </li>
    </ul>

    <button @click="submitOption">提交</button>
  </div>
</template>
