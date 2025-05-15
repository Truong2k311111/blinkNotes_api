# Sử dụng image chính thức của Gradle để build ứng dụng
FROM gradle:7.6.2-jdk17 AS build

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Build ứng dụng
RUN gradle installDist

# Sử dụng image nhẹ để chạy ứng dụng
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép ứng dụng đã build từ bước trước
COPY --from=build /app/build/install/blinkNotes /app

# Mở cổng 4040
EXPOSE 4040

# Chạy ứng dụng
CMD ["bin/blinkNotes"]