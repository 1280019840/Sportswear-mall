import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import { resolve } from "path";
const pathResolve = (dir) => resolve(__dirname, ".", dir);

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    transpileDependencies: true,
    lintOnSave: false,
    resolve: {
        // 实际的路径转换  @  -> src
        alias: {
            '@': fileURLToPath(new URL('./src',
                import.meta.url))
        }
    },
})