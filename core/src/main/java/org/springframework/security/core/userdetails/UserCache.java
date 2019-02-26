/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.security.core.userdetails;

/**
 * 提供UserDetails对象的缓存。
 * 实现类应提供合适的方法设置缓存参数（如存活时间），和/或强制在正常过期时间之前删除缓存的方法。这些不是
 * UserCache接口的一部分，因为不同的缓存系统实现（内存，硬盘，集群，混合等等）各不相同。
 * 缓存只在不维护服务端状态的应用需要，例如远程客户端或web services。认证资格在每次调用都会携带，经常性地访问数据库或别的
 * 持久化存储机制进行验证将会是一个很大的负担。这种时候，需要配置一个缓存存储UserDetails信息，而不是每次都加载。
 *
 * @see org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
 *
 * @author Ben Alex
 */
public interface UserCache {
	// ~ Methods
	// ========================================================================================================

	/**
	 * Obtains a {@link UserDetails} from the cache.
	 *
	 * @param username the {@link User#getUsername()} used to place the user in the cache
	 *
	 * @return the populated <code>UserDetails</code> or <code>null</code> if the user
	 * could not be found or if the cache entry has expired
	 */
	UserDetails getUserFromCache(String username);

	/**
	 * Places a {@link UserDetails} in the cache. The <code>username</code> is the key
	 * used to subsequently retrieve the <code>UserDetails</code>.
	 *
	 * @param user the fully populated <code>UserDetails</code> to place in the cache
	 */
	void putUserInCache(UserDetails user);

	/**
	 * Removes the specified user from the cache. The <code>username</code> is the key
	 * used to remove the user. If the user is not found, the method should simply return
	 * (not thrown an exception).
	 * <p>
	 * Some cache implementations may not support eviction from the cache, in which case
	 * they should provide appropriate behaviour to alter the user in either its
	 * documentation, via an exception, or through a log message.
	 *
	 * @param username to be evicted from the cache
	 */
	void removeUserFromCache(String username);
}
