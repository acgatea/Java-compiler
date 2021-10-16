extern __malloc
extern __exception
extern __THIS
extern __createNullString
; *****Start ConstructorDeclarationNode *****
global _java.io.PrintStream_CONSTRUCTOR_PrintStream#
_java.io.PrintStream_CONSTRUCTOR_PrintStream#:
mov ebp, esp
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; calls implicit constructor for PrintStream
extern _java.io.OutputStream_CONSTRUCTOR_OutputStream#
call _java.io.OutputStream_CONSTRUCTOR_OutputStream# ; call constructor
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

; *****initialize nonstatic fields *****
; *****implicit return at end of constructor *****
mov esp, ebp
ret ; return

; *****END ConstructorDeclarationNode *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#String#
_java.io.PrintStream_METHOD_print#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start forstmtnode *****
; *****Start LocalVariableDeclNode *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 0
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_3:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store local i
; *****END LocalVariableDeclNode *****
_java.io.PrintStream_ForLoop_1:
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_5:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

mov eax, [esp + 4 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_7
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_7:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 0 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_6:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
cmp eax, ebx
jl _java.io.PrintStream_IfLT_8
mov eax, 0
jmp _java.io.PrintStream_BinOpEndLabel_4
_java.io.PrintStream_IfLT_8:
mov eax, 1
_java.io.PrintStream_BinOpEndLabel_4:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, 0
cmp eax, ebx
je _java.io.PrintStream_doneFor_2
push esi; 
mov esi, esp ; block start
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_10:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_11
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_11:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +36] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_9:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument c

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_12
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_12:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****Start AssignmentNode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
; *****END NameNode *****
push eax ; store result of LHS
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax,  -4 ; get address of local i
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_14:
; *****END BinOpExprNode *****
push eax ;push leftexpr onto stack
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 1
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_15:
; *****END BinOpExprNode *****
mov ebx, eax ; move rightexpr to ebx
pop eax ; get leftexpr
_java.io.PrintStream_beginNonStringAdd_16:
add eax, ebx ; +
_java.io.PrintStream_endNonStringAdd_17:
_java.io.PrintStream_BinOpEndLabel_13:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
mov ebx, eax ; move value of RHS to ebx
pop eax ; get result of LHS
mov [eax], ebx ; store result of RHS at LHS
mov eax, ebx ; final result
; *****END AssignmentNode *****
mov esp, esi ; block end
pop esi
jmp _java.io.PrintStream_ForLoop_1
_java.io.PrintStream_doneFor_2:
; *****END forstmtnode *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#
_java.io.PrintStream_METHOD_println#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
; *****Start String Object Creation method *****
mov eax, 8
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _VTABLE_java.lang.String
mov ebx, [_VTABLE_java.lang.String]
mov [eax], ebx ; store vtable label
push eax ; push new THIS
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for constructor call) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Start Array Creation *****
mov eax, 0 ; array size
mov ecx, eax 
mov edx, 2
add eax, edx
shl eax, 2
push ecx ; store array length
push ebp ; save ebp
call __malloc
pop ebp ; load ebp
extern _ARRAY$_VTABLE_char
mov ebx, [_ARRAY$_VTABLE_char]
mov [eax], ebx ; store vtable label
pop ecx
mov [eax + 4], ecx ; store array length
push eax ; store array location
add eax, 8 ; starting location of actual array elements 
pop eax ; return array location
; *****END Array Creation *****
push eax ; store argument of String constructor
mov eax, [esp + 8 ] ; get address of object (for constructor call) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_20
call __exception ; nullpointerexception (for constructorcall)
_java.io.PrintStream_noError_20:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
extern _java.lang.String_CONSTRUCTOR_String#char$$#
call _java.lang.String_CONSTRUCTOR_String#char$$# ; call constructor
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop eax; load old THIS
mov edx, [__THIS]
mov [edx], eax ; update THIS to old value
pop eax; load new THIS
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_18:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_21
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_21:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#String#
_java.io.PrintStream_METHOD_println#String#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg s
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_22:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_23
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_23:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start LiteralNode *****
mov eax, 10
; *****END LiteralNode *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_24:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument c

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_25
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_25:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +32] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#Object#
_java.io.PrintStream_METHOD_println#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_27:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument o

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#Object#
call _java.lang.String_METHOD_valueOf#Object# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_26:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_28
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_28:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#boolean#
_java.io.PrintStream_METHOD_println#boolean#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_30:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument b

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#boolean#
call _java.lang.String_METHOD_valueOf#boolean# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_29:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_31
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_31:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#byte#
_java.io.PrintStream_METHOD_println#byte#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_33:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#byte#
call _java.lang.String_METHOD_valueOf#byte# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_32:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_34
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_34:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#char#
_java.io.PrintStream_METHOD_println#char#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_36:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument c

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#char#
call _java.lang.String_METHOD_valueOf#char# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_35:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_37
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_37:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#short#
_java.io.PrintStream_METHOD_println#short#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_39:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#short#
call _java.lang.String_METHOD_valueOf#short# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_38:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_40
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_40:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_println#int#
_java.io.PrintStream_METHOD_println#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_42:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#int#
call _java.lang.String_METHOD_valueOf#int# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_41:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_43
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_43:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +56] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#Object#
_java.io.PrintStream_METHOD_print#Object#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_45:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument o

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#Object#
call _java.lang.String_METHOD_valueOf#Object# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_44:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_46
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_46:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#boolean#
_java.io.PrintStream_METHOD_print#boolean#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_48:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument b

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#boolean#
call _java.lang.String_METHOD_valueOf#boolean# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_47:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_49
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_49:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#byte#
_java.io.PrintStream_METHOD_print#byte#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_51:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#byte#
call _java.lang.String_METHOD_valueOf#byte# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_50:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_52
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_52:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#char#
_java.io.PrintStream_METHOD_print#char#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_54:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument c

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#char#
call _java.lang.String_METHOD_valueOf#char# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_53:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_55
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_55:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#short#
_java.io.PrintStream_METHOD_print#short#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_57:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#short#
call _java.lang.String_METHOD_valueOf#short# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_56:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_58
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_58:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
; *****Start MethodDeclaration *****
; *****Start MethodHeader *****
global _java.io.PrintStream_METHOD_print#int#
_java.io.PrintStream_METHOD_print#int#:
mov ebp, esp
; *****END MethodHeader *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
mov edx, eax; put address of object (for nonstatic methodcalls) in edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start PrimaryNoNewArrayNode *****
; *****Start MethodInvocation node *****
mov eax, [__THIS]
mov eax, [eax]; get THIS (for prefixless-method call)
mov edx, [__THIS]
mov edx, [edx]; old THIS
push edx
; save registers
push esi
push ebx
push ecx
push edx
push ebp

; *****Compute arguments *****
; *****Start AssignmentExprNode *****
; *****Start BinOpExprNode *****
; *****Start unaryexprnode *****
; *****Start NameNode *****
mov eax , ebp
add eax, 4 ; get address of arg b
mov eax, [eax] ; not LHS so get value
; *****END NameNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_60:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument i

; *****Done computing arguments *****
mov ecx, 0
mov edx, [__THIS]
mov [edx], ecx ; update THIS to 0 (static method call)
extern _java.lang.String_METHOD_valueOf#int#
call _java.lang.String_METHOD_valueOf#int# ; call static method
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****END PrimaryNoNewArrayNode *****
; *****END unaryexprnode *****
_java.io.PrintStream_BinOpEndLabel_59:
; *****END BinOpExprNode *****
; *****END AssignmentExprNode *****
push eax ; store argument s

; *****Done computing arguments *****
mov eax, [esp + 8 ] ; get address of _THIS_ (for nonstatic methodcalls) from stack
mov edx, 0
cmp eax, edx
jne _java.io.PrintStream_noError_61
call __exception ; nullpointerexception (for methodcall)
_java.io.PrintStream_noError_61:
mov edx, [__THIS]
mov [edx], eax ; update THIS to prefix object
mov eax, [eax] ; get vtable 
mov eax , [eax +48] ; load method 
call eax 
add esp, 4 ; pop arguments 
; load registers
pop ebp
pop edx
pop ecx
pop ebx
pop esi

pop edx; load old THIS
mov ecx, [__THIS]
mov [ecx], edx ; update THIS to old value
; *****END MethodInvocation node *****
; *****implicit return at end of void method's body *****
mov esp, ebp
ret ; return

; *****END MethodDeclaration *****
